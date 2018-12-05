package com.spirngboot.common;

import java.io.Serializable;

/**
 * @author brandon
 * create on 2018-12-01
 * desc: 用于封装返回的结果
 */
public class RestResp implements Serializable {

    public final int status;
    public final String message;
    public final Object data;


    protected RestResp(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private RestResp(int status, String messsage) {
        this(status, messsage, null);
    }

    public static RestResp success(Object data) {
        return new RestResp(1, "success", data);
    }

    public static RestResp success() {
        return new RestResp(1, "success");
    }

    public static RestResp success(String message, Object data) {
        return new RestResp(1, message, data);
    }

    public static RestResp fail(String message, Object data) {
        return new RestResp(-1, message, data);
    }

    public static RestResp fail(String message) {
        return new RestResp(-1, message);
    }

    public static RestResp fail() {
        return new RestResp(-1, "fail");
    }

}
