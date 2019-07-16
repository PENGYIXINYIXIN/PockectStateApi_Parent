package com.pockectstate.api.pockectstateapi_appapi.api;

import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_appapi.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/15
 */
@RestController
@Api(value = "会员签到",tags = "会员签到")
public class SignController {
    @Autowired
    private SignService signService;
    //检查
    @ApiOperation(value = "实现检查是否可以签到",notes = "实现检查是否可以签到")
    @GetMapping("api/sign/checkday.do")
    public R checkDay(HttpServletRequest request){
        return signService.checkDay();
    }
    //签到
    @ApiOperation(value = "实现签到",notes = "实现签到")
    @GetMapping("api/sign/savesign.do")
    public R save(){
        return signService.save();
    }
    //统计
    @ApiOperation(value = "实现查询统计",notes = "实现查询统计")
    @GetMapping("api/sign/usertj.do")
    public R tj(){
        return signService.tj();
    }
    //当前月的签到数据
    @ApiOperation(value = "实现查询当前月的签到数据",notes = "实现查询当前月的签到数据")
    @GetMapping("api/sign/usercurrmonth.do")
    public R currMonth(){
        return signService.currMonth();
    }
    //用户的所有签到数据
    @ApiOperation(value = "实现查询所有签到数据",notes = "实现查询所有签到数据")
    @GetMapping("api/sign/usersignall.do")
    public R all(HttpServletRequest request){
        return signService.all();
    }
}
