package com.demo.concurrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author brandon
 * Created on 2019-08-18.
 * desc:
 **/
public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            } catch (Exception ex) {
            }
            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

    static class ConnectionPool {
        private LinkedList<Connection> pool = new LinkedList<Connection>();

        /**
         * 初始化连接池
         *
         * @param initialSize 初始化连接池的链接数量
         */
        public ConnectionPool(int initialSize) {
            if (initialSize > 0) {
                for (int i = 0; i < initialSize; i++) {
                    pool.addLast(ConnectionDriver.createConnection());
                }
            }
        }

        /**
         * 释放链接
         *
         * @param connection 要释放的链接
         */
        public void releaseConnection(Connection connection) {
            if (connection != null) {
                //用pool对象锁定
                synchronized (pool) {
                    //将链接放到pool池的末尾，并唤醒pool对象上的所有等待线程
                    //所有等待线程将会从WAITING状态进入BLOCKED状态，并争抢pool对象锁，
                    // 抢锁成功的将有机会继续执行，其他线程等待锁的释放
                    pool.addLast(connection);
                    //调用notifyAll()、notify()、wait()方法的时候需要对对象进行加锁操作
                    pool.notifyAll();
                }
            }
        }

        /**
         * 从连接池中获取一个链接，可以设置获取超时时间，如果超时未获取到链接，则返回null
         *
         * @param mills 获取链接的超时时间，单位:毫秒，如果mills的值<=0表示无超时设置，将会一直等待获取链接
         * @return 返回链接，如果超时未获取到链接，则返回null链接
         */
        public Connection fetchConnection(long mills) throws InterruptedException {
            //pool对象作为锁进行同步操作
            synchronized (pool) {
                //判断是否设置了超时时间
                if (mills <= 0) {
                    //如果pool为空，则在pool对象上等待，直到被唤醒
                    while (pool.isEmpty()) {
                        pool.wait();
                    }
                    //获取pool里的第一个链接
                    return pool.removeFirst();
                } else {
                    //设置有超时时间
                    //记录超时的预期时间，当前时间+超时时间=预期时间
                    long future = System.currentTimeMillis() + mills;
                    long remaining = mills;
                    //如果pool未空且超时时间>0,则在pool对象上等待
                    while (pool.isEmpty() && remaining > 0) {
                        //pool上的超时等待，如果remaining时间内没有唤醒，则会自己唤醒执行后面的代码
                        pool.wait(remaining);
                        //计算当前时间和预期时间的差值，如果差值>0,说明还没到达超时时间，pool对象主动唤醒,此时pool.isEmpty() == false
                        //如果差值<=0,则表示为超时唤醒，此时pool.iEmpty() == true
                        remaining = future - System.currentTimeMillis();
                    }
                    Connection result = null;
                    //不过是超时唤醒还是主动唤醒，都要判断pool内是否有值，有则获取链接，没有则返回null
                    if (!pool.isEmpty()) {
                        result = pool.removeFirst();
                    }
                    return result;
                }
            }
        }
    }

    static class ConnectionDriver {
        static class ConnectionHandler implements InvocationHandler {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("commit")) {
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                return null;
            }
        }

        public static final Connection createConnection() {
            return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionHandler());
        }
    }

}
