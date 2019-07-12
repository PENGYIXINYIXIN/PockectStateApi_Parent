package com.pockectstate.message.pockectstatemsg_servermsg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
@MapperScan("com.pockectstate.message.pockectstatemsg_servermsg.dao")
public class PockectstatemsgServermsgApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectstatemsgServermsgApplication.class, args);
    }

}
