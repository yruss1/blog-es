package com.xu.blog.common;

import java.io.Serializable;

/**

 * @author 11582
 * @description 通用返回结果
 * @date 2024/3/18  18:22
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int successCode = 200;
    private static final int errorCode = 500;
    private Boolean flag = true;
    private String message = "操作成功！";
    private Integer code = 0;
    private T result;
    private long timestamp = System.currentTimeMillis();

    private Result() {
    }

    public static <T> Result<T> ok() {
        Result<T> r = new Result<>();
        r.setFlag(true);
        r.setCode(successCode);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setFlag(true);
        r.setCode(successCode);
        r.setResult(data);
        return r;
    }

    public static <T> Result<T> ok(T data, String msg) {
        Result<T> r = new Result<>();
        r.setFlag(true);
        r.setCode(successCode);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }

    public static <T> Result<T> error(String msg) {
        return error(errorCode, msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setFlag(false);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }


    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
