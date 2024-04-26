package com.xu.blog.common.exception;

import lombok.Getter;


/**
 * @author 11582
 */
@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;

    private ApiError error;

    public ApiException(ApiError error) {
        this.error = error;
    }

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
