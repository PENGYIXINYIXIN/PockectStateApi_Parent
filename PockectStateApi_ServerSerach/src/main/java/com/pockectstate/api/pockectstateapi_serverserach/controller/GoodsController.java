package com.pockectstate.api.pockectstateapi_serverserach.controller;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_serverserach.model.Goods;
import com.pockectstate.api.pockectstateapi_serverserach.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("search/goodslike.do")
    public R search(@RequestParam String msg, HttpServletRequest request){
        R save = goodsService.searchLike(msg,request.getRemoteAddr());
        return save;
    }
}
