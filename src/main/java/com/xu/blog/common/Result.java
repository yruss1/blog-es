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
    public static final String SUCCESS = "成功";
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 500;
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

    public static <T> Result<T> info(String info) {
        Result<T> r = new Result<>();
        r.setFlag(true);
        if (info.contains(SUCCESS)){
            r.setCode(SUCCESS_CODE);
        }else {
            r.setCode(ERROR_CODE);
        }
        r.setMessage(info);
        return r;
    }

    public static <T> Result<T> ok() {
        Result<T> r = new Result<>();
        r.setFlag(true);
        r.setCode(SUCCESS_CODE);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setFlag(true);
        r.setCode(SUCCESS_CODE);
        r.setResult(data);
        return r;
    }

    public static <T> Result<T> ok(T data, String msg) {
        Result<T> r = new Result<>();
        r.setFlag(true);
        r.setCode(SUCCESS_CODE);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }

    public static <T> Result<T> error(String msg) {
        return error(ERROR_CODE, msg);
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
