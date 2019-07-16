package com.pockectstate.api.pockectstateapi_serveruser.controller;

import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_serveruser.service.UserSignService;
import com.pockectstate.entity.user.UserSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/15
 */
@RestController
public class UserSignController {
    @Autowired
    private UserSignService userSignService;

    //检查
    @GetMapping("sign/checkday.do")
    public R checkDay(HttpServletRequest request){
        return userSignService.checkSign(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
    //签到
    @GetMapping("sign/savesign.do")
    public R save(HttpServletRequest request){
        return userSignService.save(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
    //统计
    @GetMapping("sign/usertj.do")
    public R tj(HttpServletRequest request){
        return userSignService.queryTj(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
    //当前月的签到数据
    @GetMapping("sign/usercurrmonth.do")
    public R currMonth(HttpServletRequest request){
        return userSignService.queryByUid(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
    //用户的所有签到数据
    @GetMapping("sign/usersignall.do")
    public R all(HttpServletRequest request){
        return userSignService.queryUidAll(request.getHeader(Jwt_Config.HEADERTOKEN));
    }
}
