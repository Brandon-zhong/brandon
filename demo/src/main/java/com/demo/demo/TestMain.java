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


    public static void main(String[] args) {
        Optional<String> optional = Optional.ofNullable("hahah");
        Object object = optional.orElse("this is or else");
        System.out.println("object --> " +   object);
    }

}
