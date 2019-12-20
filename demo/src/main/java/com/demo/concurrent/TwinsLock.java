package com.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author brandon
 * create on 2019-11-04
 * desc:
 */
public class TwinsLock implements Lock {

    private static final class Sync extends AbstractQueuedSynchronizer {

        public Sync(int num) {
            if (num < 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(num);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            while (true) {
                int state = getState();
                int newState = state - arg;
                if (newState >= 0 && compareAndSetState(state, newState)) {
                    return newState;
                }
            }
        }


        @Override
        protected boolean tryReleaseShared(int arg) {
            while (true) {
                int state = getState();
                int newState = state + arg;
                if (newState <= 2 && compareAndSetState(state, newState)) {
                    return true;
                }
            }
        }

    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        for (int i = 0; i < 10; ++i) {
            SleepUtils.second(1);
            System.out.println();
        }

    }

}
