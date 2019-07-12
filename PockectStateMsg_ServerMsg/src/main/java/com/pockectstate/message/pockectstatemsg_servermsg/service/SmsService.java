package com.pockectstate.message.pockectstatemsg_servermsg.service;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.msg.SmsSend;

/**
 * @author:Yixi
 * @date:2019/7/10
 */
public interface SmsService {
    R  save(SmsSend smsSend);
    R checkCode(String phone,int code);
    R sendSms(String phone,String ip);
}
