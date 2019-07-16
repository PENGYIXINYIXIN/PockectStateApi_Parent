package com.pockectstate.api.pockectstateapi_servicelogin.config;

import com.pockectstate.api.common.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:Yixi
 * @date:2019/7/12
 */
@Configuration
public class IdAuToConfig {
    @Bean
    public IdGenerator createId(){
        return new IdGenerator();
    }
}
