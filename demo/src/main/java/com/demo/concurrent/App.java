package com.demo.concurrent;

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


        for (int i = 0; i < count; ++i) {
            new Thread(() -> {
                threadLatch.countDown();
                System.out.println(Thread.currentThread().getName());
                try {
                    mainLatch.await();
                    demo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }

        threadLatch.await();
        mainLatch.countDown();
        Thread.sleep(2000);
        System.out.println("end  " + i);


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
