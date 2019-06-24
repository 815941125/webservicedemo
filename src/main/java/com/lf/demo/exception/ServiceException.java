package com.lf.demo.exception;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc
 * @see
 * @since 1.0
 */
public class ServiceException extends RuntimeException {

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    private Integer errorCode;

    public ServiceException() {

    }

    public ServiceException(Integer errorCode) {
        this.errorCode = errorCode;
    }


}
