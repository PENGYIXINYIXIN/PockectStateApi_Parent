package com.pockectstate.api.common.util;

import com.alibaba.fastjson.JSON;
import com.pockectstate.api.common.model.JuheSms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class JuHeSms_Util {
    public static final String SMS_URL="http://v.juhe.cn/sms/send";
    public static final int TPL_Id=171949;
    public static final String SMS_KEY="bd5b5f8943295efaa76749a033bb7d80";

    public static boolean sendMsg(String phone,int code) throws UnsupportedEncodingException {
        StringBuffer buffer=new StringBuffer(SMS_URL);
        buffer.append("?mobile="+phone);
        buffer.append("&tpl_id="+TPL_Id);
        buffer.append("&tpl_value="+ URLEncoder.encode("#code#="+code,"UTF-8"));
        buffer.append("&key="+SMS_KEY);
        String json=Http_Util.getStr(buffer.toString());
        System.out.println(json);
        if(json!=null) {
            JuheSms sms = JSON.parseObject(json, JuheSms.class);
            return sms.getError_code() == 0;
        }else {
            return false;
        }
    }
}
