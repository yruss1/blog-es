package com.xu.blog.exception;

import lombok.Getter;

/**
 * An exception which can occur while invoking methods of the Bybit API.
 */
@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by Bybit API.
     * -- GETTER --
     */
    private ApiError error;

    /**
     * Instantiates a new Bybit api exception.
     *
     * @param error an error response object
     */
    public ApiException(ApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new Bybit api exception.
     */
    public ApiException() {
        super();
    }

    /**
     * Instantiates a new Bybit api exception.
     *
     * @param message the message
     */
    public ApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Bybit api exception.
     *
     * @param cause the cause
     */
    public ApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Bybit api exception.
     *
     * @param message the message
     * @param cause the cause
     */
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
