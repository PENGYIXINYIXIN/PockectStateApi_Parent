package com.pockectstate.api.pockectstateapi_appapi.service;

import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/13
 */
@FeignClient(name = "LoginProvider")
public interface LoginService {
    //登陆
    @PostMapping("auth/login.do")
    public R login(@RequestBody LoginDto loginDto);
    //注销p
    @GetMapping("auth/loginout.do")
    public R loginout();
    //检查令牌是否有效
    @GetMapping("auth/checktoken.do")
    public  R check();
    // 找回密码
    @PutMapping("auth/getbackpassword.do")
    public  R getbacks(@RequestBody UserDto userDto);


}
