package com.demo.demo;

/**
 * @author brandon
 * Created on 2018-12-31.
 * desc:
 */
public enum  EnumSingletonTest {

    SINGLETON_TEST;

    private String name = "this is enum   ";

    public String getString() {
        return SINGLETON_TEST.name;
    }
}
