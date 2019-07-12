package com.pockectstate.api.pockectstateapi_appapi.api;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_appapi.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
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
@Api(value = "短信操作",tags = "短信操作")
public class SmsController {
    @Autowired
    private SmsService smsService;
    //发送短信验证码
    @ApiOperation(value = "实现短信验证码的发送",notes = "实现短信验证码的发送")
    @PostMapping("api/message/sendsmscode.do")
    public R sendSms(@RequestParam("phone")String phone){
        return smsService.sendSms(phone);
    }

    //校验短信验证码
    @ApiOperation(value = "实现短信验证码的校验",notes = "实现短信验证码的校验")
    @GetMapping("api/message/checksmacode.do")
    public R check(@RequestParam("phone")String phone,@RequestParam("code")int code ){
        return smsService.check(phone,code);
    }
}
