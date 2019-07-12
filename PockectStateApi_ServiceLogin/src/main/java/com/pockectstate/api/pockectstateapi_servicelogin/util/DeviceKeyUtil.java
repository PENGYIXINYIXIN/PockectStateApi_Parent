package com.pockectstate.api.pockectstateapi_servicelogin.util;

import com.pockectstate.api.common.model.JWTToken;

/**
 * @author:Yixi
 * @date:2019/7/12
 */
public class DeviceKeyUtil {
    public static  String createKey(JWTToken jwtToken){
        StringBuffer buffer = new StringBuffer();
        buffer.append(jwtToken.getPhone());
        buffer.append("_");
        buffer.append(jwtToken.getDevice());
        buffer.append("_");
        buffer.append(jwtToken.getDeviceMac());
        return buffer.toString();
    }
}
