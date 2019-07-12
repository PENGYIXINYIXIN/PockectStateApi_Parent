package com.pockectstate.message.pockectstatemsg_servermsg.controller;

import com.netflix.discovery.converters.Auto;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.message.pockectstatemsg_servermsg.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;


    //发送短信验证码
    @PostMapping("message/sendsmscode.do")
    public R sendSms(@RequestParam("phone")String phone, HttpServletRequest  request){
        return smsService.sendSms(phone,request.getRemoteAddr());
    }

    //校验短信验证码
    @GetMapping("message/checksmacode.do")
    public R check(@RequestParam("phone")String phone,@RequestParam("code")int code ){
        return smsService.checkCode(phone,code);
    }
}
