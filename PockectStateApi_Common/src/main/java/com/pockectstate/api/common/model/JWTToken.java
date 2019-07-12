package com.pockectstate.api.common.model;

import lombok.Data;

/**
 * @author:Yixi
 * @date:2019/7/12
 * JWT里面存的content 就是存这个 有效负载
 * JWT实现登陆授权的令牌
 */

@Data
public class JWTToken {
    private  int id;
    private  String phone;//手机号
    public  String no; //令牌的序号
    private int device;//设备类型
    private String deviceMac;//设备的Mac地址  唯一的

}
