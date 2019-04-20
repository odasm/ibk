package com.javalitterboy.ibk.exception;

import com.javalitterboy.ibk.constant.StatusCode;

/**
 * @author 14183
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int code;

    public ServiceException() {
        super("未知错误");
        this.code = StatusCode.SC_ERROR;
    }

    public ServiceException(String message) {
        super(message);
        this.code = StatusCode.SC_ERROR;
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
        this.code = StatusCode.SC_INTERNAL_SERVER_ERROR;
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, int code, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
