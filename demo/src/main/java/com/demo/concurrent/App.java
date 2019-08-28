package com.demo.concurrent;

import java.util.stream.Stream;

/**
 * @author brandon
 * create on 2019-07-01
 * desc:
 */
public class App {
    public static void main(String[] args) {

        final int[] count = {0};
        int total = 10;
        final Thread[] threads = new Thread[total];
        final Object lock = new Object();
        for (int i = 0; i < total; ++i) {
            Thread thread = new Thread(() -> {
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                    }
                    ++count[0];
                    SleepUtils.second(1);
                }
            }, "thread id --> " + i);
            thread.setDaemon(true);
            threads[i] = thread;
            thread.start();
        }

        //起一条线程来查看threads里的所有线程的状态
        Thread monitor = new Thread(() -> {
            while (true) {
                Stream.of(threads).forEach((thread) -> {
                    System.out.print(thread.getState().name() + " ");
                });
                System.out.print("\n");
                SleepUtils.second(1);
            }
        });
        //将监控线程daemon设置为true，使得线程随着jvm结束而关闭
        monitor.setDaemon(true);
        monitor.start();

        System.out.println("all thread is wait()");
        SleepUtils.second(2);
        synchronized (lock) {
            lock.notify();
        }
        System.out.println("all thread is notifyAll()");
        SleepUtils.second(15);
        System.out.println("App.main --> " + count[0]);
    }


}
