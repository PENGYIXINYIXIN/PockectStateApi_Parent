package com.pockectstate.api.pockectstateapi_appapi.service;


import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author:Yixi
 * @date:2019/7/15
 */
@FeignClient(name = "UserProvider")
public interface SignService {
    //检查
    @GetMapping("sign/checkday.do")
     R checkDay();
    //签到
    @GetMapping("sign/savesign.do")
     R save();
    //统计
    @GetMapping("sign/usertj.do")
     R tj();
    //当前月的签到数据
    @GetMapping("sign/usercurrmonth.do")
     R currMonth();
    //用户的所有签到数据
    @GetMapping("sign/usersignall.do")
     R all();
}
