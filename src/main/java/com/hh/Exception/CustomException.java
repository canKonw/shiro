package com.hh.Exception;

/**
 * Created by hh on 15-10-21.
 */
public class CustomException extends Exception{
    private String msg;
    public  CustomException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
