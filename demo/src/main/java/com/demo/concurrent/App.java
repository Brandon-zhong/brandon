package com.demo.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author brandon
 * create on 2019-07-01
 * desc:
 */
public class App {
    static class Demo extends RecursiveTask<Integer> {

        @Override
        protected Integer compute() {
            return null;
        }
    }

    class DemoThread implements Runnable {
        @Override
        public void run() {
            System.out.println("DemoThread.run   " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {



        /*new Demo().fork();

        ReentrantLock lock = new ReentrantLock();

        Thread thread = new Thread(() -> {
            lock.lock();
            try {
                int i = 0;
                System.out.println(Thread.currentThread().getName());
                Map<Integer, Object> map = new HashMap<>();
                while (i++ < 10000) {
                    map.put(i, new Object());
                    if (i == 10000) {
                        Thread.sleep(100);
                        i = 0;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "lock-thread");
        thread.start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "   " + System.currentTimeMillis());
            thread.interrupt();
        }, "interrupt-thread").start();

        Thread.sleep(100);
        System.out.println("sfsafasf");
        lock.lock();
        System.out.println("App.main");
        lock.unlock();*/
    }
}
