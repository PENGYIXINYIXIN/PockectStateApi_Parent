package com.pockectstate.api.pockectstateapi_appapi.service;

import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Yixi
 * @date:2019/7/22
 */
@FeignClient(name = "SearchProvider")
public interface SearchService {
    @GetMapping("search/goodslike.do")
     R search(@RequestParam String msg);
}
