package com.xu.blog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**

 * @author 11582
 * @date 2024/3/18  18:22
 */
@ApiModel("结果返回包装类")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int successCode = 200;
    private static final int errorCode = 500;
    @ApiModelProperty("标识")
    private Boolean flag = true;
    @ApiModelProperty("返回提示信息")
    private String message = "操作成功！";
    @ApiModelProperty("返回状态码")
    private Integer code = 0;
    @ApiModelProperty("返回结果")
    private T result;
    @ApiModelProperty("返回时间")
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
