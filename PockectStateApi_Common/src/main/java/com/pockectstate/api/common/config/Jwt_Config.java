package com.pockectstate.api.common.config;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
public class Jwt_Config {
    public static final String JWTKEY="pockectstate_java1902";
    //令牌的失效时间 单位分钟
    public static final int JETTOKENTIME=30;

    //传递的消息头名称 记录令牌
    public static  final String HEADERTOKEN="usertoken";

}
