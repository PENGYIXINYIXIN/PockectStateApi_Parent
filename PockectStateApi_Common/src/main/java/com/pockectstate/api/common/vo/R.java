package com.pockectstate.api.common.vo;

import com.pockectstate.api.common.config.ResultJson_Config;

/**
 *@Author feri
 *@Date Created in 2019/7/9 15:44
 */

public class R<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R() {
    }
    public static <T> R setOK(String msg,T obj){
        R r=new R(ResultJson_Config.OK,msg);
        r.setData(obj);
        return r;
    }
    public static <T> R setERROR(String msg,T obj){
        R r=new R(ResultJson_Config.ERROR,msg);
        r.setData(obj);
        return r;
    }
    public static <T> R setR(boolean issuccess,String msg,T obj){
        R r;
        if(issuccess){
            r=new R(ResultJson_Config.OK,msg);
        }else {
            r=new R(ResultJson_Config.ERROR,msg);
        }
        r.setData(obj);
        return r;
    }

}
