package com.demo.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author brandon
 * Created on 2019-08-18.
 * desc:
 **/
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {

        Thread waitThread = new Thread(() -> {
            //加锁，持有lock的monitor
            synchronized (lock) {
                //刚开始条件满足，进入while循环
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ "
                                + new SimpleDateFormat(" HH:mm:ss ").format(new Date()));
                        //lock的wait，暂停当前线程，释放lock锁,当前线程存入锁对象的等待队列，状态有RUNNING变为WAITING
                        lock.wait();
                        System.out.println("return from lock.wait() ---> "
                                + new SimpleDateFormat(" HH:mm:ss ").format(new Date()));
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. running @ "
                        + new SimpleDateFormat(" HH:mm:ss ").format(new Date()));
            }
        }, "waitThread");
        waitThread.start();
        SleepUtils.second(1);
        Thread notifyThread = new Thread(() -> {
            //锁定lock，因为waitThread 调用了lock.wait()释放了锁，所以这里可以获取到锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //唤醒lock上的所有等待线程，唤醒的线程不会马上从lock.wait()方法处返回，而是要等获取到lock锁才能返回
                //lock.notify() 或lock.notifyAll()将一个或全部线程从等待队列中移到同步队列中，状态有WAITING变成BLOCKED
                lock.notifyAll();
                flag = false;
                //这里线程休眠了5秒却没有释放锁，所有上面的notifyAll虽然唤醒了其他线程，但是其他线程还是不能执行
                //直到这个线程释放了lock锁，其他线程才能从wait方法处返回继续执行
                SleepUtils.second(5);
            }
            //这里再次加锁，所以其他线程还没从lock.wait()方法处返回，只有等这个lock释放后其他线程才能继续执行
            //lock.notify()释放锁后，这里的再次加锁其实是和其他线程争抢lock，只有争抢到了lock才能继续执行
            //所有这里可能有两种情况，一种是这里抢到了锁，这里执行，其他线程继续等待，
            // 另一种是其他线程抢到了锁从lock.wait()处返回了继续执行，这里等待锁释放
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }, "notifyThread");
        notifyThread.start();
        SleepUtils.second(3);


    }

}
