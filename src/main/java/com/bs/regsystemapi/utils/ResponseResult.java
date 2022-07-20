package com.bs.regsystemapi.utils;


import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseResult extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ResponseResult() {
        put("code", 200);
        put("msg", "ok");
    }

    public static ResponseResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static ResponseResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResponseResult error(int code, String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("code", code);
        responseResult.put("msg", msg);
        return responseResult;
    }

    public static ResponseResult ok(String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("msg", msg);
        return responseResult;
    }

    public static ResponseResult ok(Map<String, Object> map) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.putAll(map);
        return responseResult;
    }

    public static ResponseResult ok() {
        return new ResponseResult();
    }

    @Override
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResponseResult put(Object value) {
        super.put("data", value);
        return this;
    }
}
