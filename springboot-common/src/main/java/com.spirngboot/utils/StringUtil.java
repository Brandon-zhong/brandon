package com.spirngboot.utils;

/**
 * @author brandon
 * Created on 2018-12-01.
 * desc: 字符串工具类
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 判断该字符串是否为空,空的定义:不为null,且不为一个或多个空格
     *
     * @param str 要判断的字符串
     * @return 返回该字符是否为空的boolean值
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

}
