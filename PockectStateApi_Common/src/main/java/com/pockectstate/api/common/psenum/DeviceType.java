package com.pockectstate.api.common.psenum;

/**
 * @author:Yixi
 * @date:2019/7/12
 */
public enum DeviceType {
    android(1),iosphone(2),pchtml(3),wechat(4);
    private int value;
    private  DeviceType(int value){
        this.value= value;
    }
    public  int getValue(){
        return value;
    }
}
