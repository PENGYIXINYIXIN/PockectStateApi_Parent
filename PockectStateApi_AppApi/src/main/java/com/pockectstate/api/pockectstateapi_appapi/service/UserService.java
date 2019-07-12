package com.pockectstate.api.pockectstateapi_appapi.service;

import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author:Yixi
 * @date:2019/7/10
 */
@FeignClient(name ="UserProvider")
public interface UserService {
    @GetMapping("user/checkphone.do")
    R check(@RequestParam("phone")String phone);

    @PostMapping("user/register.do")
    R save(@RequestBody UserDto userDto);

}
