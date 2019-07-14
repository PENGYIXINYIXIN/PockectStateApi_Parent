package com.pockectstate.api.pockectstateapi_servicelogin.controller;

import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_servicelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/13
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    //登陆
    @PostMapping("auth/login.do")
    public R login(@RequestBody LoginDto loginDto){
        return  userService.login(loginDto);
    }
    //注销p
    @GetMapping("auth/loginout.do")
    public R loginout(HttpServletRequest request){
        return userService.loginout(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
    //检查令牌是否有效
    @GetMapping("auth/checktoken.do")
    public  R check(HttpServletRequest request){
        return userService.checkToken(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
    // 找回密码
    @PutMapping("auth/getbackpassword.do")
    public  R getback(@RequestBody UserDto userDto){
        return userService.goback(userDto);
    }

}

