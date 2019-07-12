package com.pockectstate.api.common.dto;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
public class UserDto {
     private  String phone ;
     private  String psw;


    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
