package com.pockectstate.api.common.dto;

import lombok.Data;

/**
 * @author:Yixi
 * @date:2019/7/12
 */
@Data
public class LoginDto {
    private  String phone;//手机号
    public  String password; //令牌的序号
    private int device;//设备类型
    private String deviceMac;//设备的Mac地址  唯一的 app 和小程序获取的是mac地址 如果是网页记录IP
}
