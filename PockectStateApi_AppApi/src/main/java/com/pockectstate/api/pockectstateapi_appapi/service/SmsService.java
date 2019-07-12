package com.pockectstate.api.pockectstateapi_appapi.service;

import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
@FeignClient(name="MsgProvider")
public interface SmsService {
    //发送短信验证码
    @PostMapping("message/sendsmscode.do")
    R sendSms(@RequestParam("phone")String phone);

    //校验短信验证码
    @GetMapping("message/checksmacode.do")
    R check(@RequestParam("phone")String phone,@RequestParam("code")int code );

}
