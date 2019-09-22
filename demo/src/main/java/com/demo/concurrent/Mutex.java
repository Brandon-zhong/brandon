package com.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author brandon
 * Created on 2019-09-21.
 * desc:
 **/
public class Mutex implements Lock {
    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 是否处于独占状态
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 独占式的获取状态
         */
        @Override
        protected boolean tryAcquire(int arg) {
            //cas更新状态，更新成功则设置当前线程独占并返回true，否则返回false
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 独占式的释放状态
         */
        @Override
        protected boolean tryRelease(int arg) {
            //如果状态为0，则已经释放，报错
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            //状态不为0，则设置为0，并且将独占线程置为null
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        /**
         * 返回一个新的condition，每个condition里包含一个队里
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }


}
