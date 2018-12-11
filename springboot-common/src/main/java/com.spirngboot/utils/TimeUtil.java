package com.spirngboot.utils;

import java.util.Date;

/**
 * @author brandon
 * Created on 2018-12-09.
 * desc: 时间工具类
 */
public class TimeUtil {

    private TimeUtil() {
    }

    /**
     * 获取当前时间的秒钟值
     *
     * @return
     */
    public static int getTimeSecend() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取指定时间的秒钟值
     *
     * @param date 指定的时间
     * @return 返回指定时间的秒钟值
     */
    public static int getTimeSecend(Date date) {
        return (int) (date.getTime() / 1000);
    }

}
