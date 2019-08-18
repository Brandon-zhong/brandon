package com.demo.concurrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
        // ᕚᑕහᰁ҅ݢզץදᕚᑕහᰁᬰᤈᥡ੊
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

        public ConnectionPool(int initialSize) {
            if (initialSize > 0) {
                for (int i = 0; i < initialSize; i++) {
                    pool.addLast(ConnectionDriver.createConnection());
                }
            }
        }

        public void releaseConnection(Connection connection) {
            if (connection != null) {
                synchronized (pool) {
                    pool.addLast(connection);
                    pool.notifyAll();
                }
            }
        }

        public Connection fetchConnection(long mills) throws InterruptedException {
            synchronized (pool) {
                if (mills <= 0) {
                    while (pool.isEmpty()) {
                        pool.wait();
                    }
                    return pool.removeFirst();

                } else {
                    long future = System.currentTimeMillis() + mills;
                    long remaining = mills;
                    while (pool.isEmpty() && remaining > 0) {
                        pool.wait(remaining);
                        remaining = future - System.currentTimeMillis();
                    }
                    Connection result = null;
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
                    TimeUnit.MILLISECONDS.sleep(100);
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
