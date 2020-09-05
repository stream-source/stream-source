package com.qxy.exception;

/**
 * @Author:qxy
 * @Date:2020/4/7 14:29
 */
public class CustomException extends Exception{

    private Integer code;

    private String message;

    public CustomException() {

    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
