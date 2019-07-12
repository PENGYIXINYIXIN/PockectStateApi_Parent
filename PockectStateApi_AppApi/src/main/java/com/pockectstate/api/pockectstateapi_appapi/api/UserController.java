package com.pockectstate.api.pockectstateapi_appapi.api;

import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_appapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author:Yixi
 * @date:2019/7/10
 */
@Api(value = "会员服务",tags = "会员服务")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "校验手机号码是否存在",notes = "校验手机号码是否存在")
    @GetMapping("api/user/checkphone.do")
     public R check(@RequestParam("phone")String phone){
        return userService.check(phone);
    }

    @ApiOperation(value = "注册会员", notes = "注册会员")
    @PostMapping("api/user/register.do")
     public  R save(@RequestBody UserDto userDto){
        return  userService.save(userDto) ;
    }
}
