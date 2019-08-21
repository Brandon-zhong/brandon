package com.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author brandon
 * Created on 2019-08-17.
 * desc:
 **/
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

}
