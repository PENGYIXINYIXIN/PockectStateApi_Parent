package com.pockectstate.api.pockectstateapi_appapi.api;

import com.netflix.discovery.converters.Auto;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.api.pockectstateapi_appapi.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@RestController
@Api(value = "站内搜索",tags = "站内搜索")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "站内搜索",notes = "站内搜索 基于ElasticSearch")
    @GetMapping("api/search/goodslike.do")
     public R search(@RequestBody String msg){
        return searchService.search(msg);
    };
}
