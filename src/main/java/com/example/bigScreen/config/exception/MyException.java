package com.example.bigScreen.config.exception;

/**
 * @author: SuKai
 * @date: 2022/4/13
 * @time: 10:04
 * @description: 自定义异常类
 */
public class MyException extends RuntimeException{

    private int code;
    private String msg;

    public MyException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
