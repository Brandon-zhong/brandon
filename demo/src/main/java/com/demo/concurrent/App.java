package com.demo.concurrent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author brandon
 * create on 2019-07-01
 * desc:
 */
public class App {

    private static ReentrantLock lock = new ReentrantLock();

    private static final int count = 4;

    private static int i = 1;

    private static final CountDownLatch mainLatch = new CountDownLatch(1);
    private static final CountDownLatch threadLatch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {


        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();

    }

    private static void demo() {
        lock.lock();
        try {
            ++i;
            System.out.println("App.demo  " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


}
