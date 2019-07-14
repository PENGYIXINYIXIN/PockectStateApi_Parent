package com.pockectstate.api.common.config;

/**
 * @author:Yixi
 * @date:2019/7/10
 * 基于redis数据， 对应的key的值
 */
public class RedisKey_Config {
    //记录手机短信的验证码  五分钟失效
    public  static  final  String VCODE_CODE="vc:";
    //记录一分钟内容
    public  static  final  String VCODE_FIRST="vc_first";
    //记录十分钟 三次
    public  static  final  String VCODE_SECOND="vc_second";
    //记录一小时 四次
    public  static  final  String VCODE_THREE="vc_three";
    //记录一天  二十次
    public  static  final  String VCODE_FOUR="vc_four";

    //记录登陆令牌相关的key
    public  static  final  String JWTTOKEN_DEVICE="jwtdevice:";//手机号_设备类型_Mac地址
    public  static  final  String JWTTOKEN_TOKEN="jwttoken:";//令牌

    //记录登陆失败相关的
    //登陆失败次数
    public  static  final  String LOGINGERROR="loginerror";//phone失效期 15分钟
    //记录冻结的账号以及时间
    public  static  final  String LOGINGFORCE="loginforce:";//phone 失效期  15分钟






}
