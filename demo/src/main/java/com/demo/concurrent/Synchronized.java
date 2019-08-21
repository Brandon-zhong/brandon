package com.demo.concurrent;

/**
 * @author brandon
 * Created on 2019-08-17.
 * desc:
 **/
public class Synchronized {

    public static void main(String[] args) {
        synchronized (Synchronized.class) {
        }
        m();
    }
    public static synchronized void m() {
    }
}
