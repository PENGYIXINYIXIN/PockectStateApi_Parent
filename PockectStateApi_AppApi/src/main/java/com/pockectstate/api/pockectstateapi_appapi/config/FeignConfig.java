package com.pockectstate.api.pockectstateapi_appapi.config;

import com.pockectstate.api.common.config.Jwt_Config;
import com.pockectstate.api.common.config.RedisKey_Config;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author feri
 *@Date Created in 2019/7/15 16:34
 * 自定义类实现Feign的消息头传递数据
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //获取所有请求消息头
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //将消息头指定内容写出 传递给提供者
        requestTemplate.header(Jwt_Config.HEADERTOKEN,request.getHeader(Jwt_Config.HEADERTOKEN));
    }
}
