package com.demo.concurrent;

/**
 * @author brandon
 * Created on 2019-08-18.
 * desc:
 **/
public class Join {

    public static void main(String[] args) {

        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(new Domino(previous), i + "");
            thread.start();
            previous = thread;
        }
        SleepUtils.second(5);
        System.out.println(Thread.currentThread().getName() + "----");


    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -----");
        }
    }

}
