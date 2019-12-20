package com.demo.demo;

import java.util.Optional;

/**
 * @author brandon
 * Created on 2018-12-31.
 * desc:
 */
public class TestMain {

//    public static void main(String[] args) {
//
//        reverse(-189);
//
//    }


    public static int reverse(int x) {

        String number = Integer.toString(x);
        System.out.println("number -- > " + number);

        boolean bool = number.contains("-");
        String a = "";
        if (bool) {
            a = number.substring(0, 1);
        }
        char[] chars = number.replaceAll("-", "").toCharArray();
        for (int i = chars.length; i >= 0; i--) {

        }
        return 0;
    }


    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    public static void main(String[] args) {

        System.out.println("COUNT_MASK  000--> " + Integer.toBinaryString(COUNT_MASK));
        System.out.println("RUNNING     --> " + Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN    --> " + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP        --> " + Integer.toBinaryString(STOP));
        System.out.println("TIDYING     --> " + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED  --> " + Integer.toBinaryString(TERMINATED));

    }

}
