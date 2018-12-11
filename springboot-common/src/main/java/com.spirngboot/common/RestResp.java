package com.spirngboot.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brandon
 * create on 2018-12-01
 * desc: 用于封装返回的结果
 */
public class RestResp extends HashMap implements Serializable {

//    private int status;
//    private String message;
//    private Object data;

    private String errorCode = "errorCode";
    private String data = "data";
    private String success = "success";
    private String msg = "msg";


    protected RestResp(int errorCode, String message, Object data) {
        this.put(this.errorCode, errorCode);
        this.put(this.data, data);
        this.put(this.msg, message);
        if (errorCode > 0) {
            this.put(this.success, false);
        } else {
            this.put(this.success, true);
        }
    }

    public RestResp putResp(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public RestResp putAllResp(Map map) {
        this.putAll(map);
        return this;
    }

    private RestResp(int status, String messsage) {
        this(status, messsage, null);
    }

    public static RestResp success(Object data) {
        return new RestResp(0, "success", data);
    }

    public static RestResp success() {
        return new RestResp(0, "success");
    }

    public static RestResp success(String message, Object data) {
        return new RestResp(0, message, data);
    }

    public static RestResp fail(String message, Object data) {
        return new RestResp(404, message, data);
    }

    public static RestResp fail(String message) {
        return new RestResp(404, message);
    }

    public static RestResp fail() {
        return new RestResp(404, "fail");
    }

}
