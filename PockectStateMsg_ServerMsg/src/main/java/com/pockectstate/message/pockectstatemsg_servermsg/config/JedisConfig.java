package com.pockectstate.message.pockectstatemsg_servermsg.config;

import com.pockectstate.api.common.util.JedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Author feri
 *@Date Created in 2019/7/10 14:49
 */
@Configuration
public class JedisConfig {
    @Bean
    public JedisUtil createJU(){
        return JedisUtil.getInstance();
    }
}
