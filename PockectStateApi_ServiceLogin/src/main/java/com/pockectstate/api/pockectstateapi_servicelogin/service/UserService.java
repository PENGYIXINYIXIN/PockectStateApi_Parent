package com.pockectstate.api.pockectstateapi_servicelogin.service;

import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;

/**
 * @author:Yixi
 * @date:2019/7/11
 */
public interface UserService {
    //登陆
    R login(LoginDto loginDto);
    //注销
    R loginout(String token);
    //找回密码
    R goback(UserDto userDto);
    //校验令牌
    R checkToken(String token);
}
