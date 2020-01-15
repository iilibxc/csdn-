package com.alliance.exception;

/**
 * ClassName:    ServiceException
 * Package:    com.alliance.exception
 * Description:
 * Datetime:    2020/1/14   11:27
 * Author:   XXXXX@XX.com
 */
/**
 * 业务逻辑异常
 */
public class ServiceException extends RuntimeException{

    private String code;
    private String msg;

    public ServiceException() {
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}