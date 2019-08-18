package com.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author brandon
 * Created on 2019-08-17.
 * desc:
 **/
public class Interrupted {

    public static void main(String[] args) throws Exception {

        Thread sleepRunner = new Thread(() -> {
            while (true) {
                SleepUtils.second(1);
            }
        }, "sleepRunner");
        Thread busyRunner = new Thread(() -> {
            while (true) {
//                System.out.println("Interrupted.main.busyRunner");
            }
        }, "busyRunner");

        sleepRunner.setDaemon(true);
        busyRunner.setDaemon(true);

        sleepRunner.start();
        busyRunner.start();

        SleepUtils.second(5);

        sleepRunner.interrupt();
        busyRunner.interrupt();
        System.out.println("sleepRunner interrupted is " + sleepRunner.isInterrupted());
        System.out.println("busyRunner interrupted is " + busyRunner.isInterrupted());
        SleepUtils.second(2);
    }


}
