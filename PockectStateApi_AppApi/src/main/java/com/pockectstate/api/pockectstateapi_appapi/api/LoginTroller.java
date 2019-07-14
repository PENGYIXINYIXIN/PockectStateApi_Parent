package com.pockectstate.api.pockectstateapi_appapi.api;

import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_appapi.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/13
 */
@Api(value = "统一鉴权中心" ,tags = "统一鉴权中心")
@RestController
public class LoginTroller {
    @Autowired
    private LoginService loginService;
    //登陆
    @ApiOperation( value = "登陆",notes ="实现登陆,基于JWT" )
    @PostMapping("auth/login.do")
    public R login(@RequestBody LoginDto loginDto){
        return  loginService.login(loginDto);
    }
    //注销p
    @ApiOperation( value = "注销",notes ="实现注销" )
    @GetMapping("auth/loginout.do")
    public R loginout(HttpServletRequest request){
        return loginService.loginout();
    }
    //检查令牌是否有效
    @ApiOperation( value = "检查令牌是否有效",notes ="检查令牌是否有效" )
    @GetMapping("auth/checktoken.do")
    public  R check(HttpServletRequest request){
        return loginService.check();
    }
    // 找回密码
    @ApiOperation( value = "找回密码",notes ="实现找回密码" )
    @PutMapping("auth/getbackpassword.do")
    public  R check(@RequestBody UserDto userDto){
        return loginService.getbacks(userDto);
    }

}
