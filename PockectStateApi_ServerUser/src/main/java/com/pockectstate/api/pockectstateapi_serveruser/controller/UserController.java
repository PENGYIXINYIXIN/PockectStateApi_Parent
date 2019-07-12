package com.pockectstate.api.pockectstateapi_serveruser.controller;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.pockectstateapi_serveruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("user/checkphone.do")
    public R check(@RequestParam("phone")String phone){
        return userService.queryByPhone(phone);
    }
    @PostMapping("user/register.do")
    public R save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
}
