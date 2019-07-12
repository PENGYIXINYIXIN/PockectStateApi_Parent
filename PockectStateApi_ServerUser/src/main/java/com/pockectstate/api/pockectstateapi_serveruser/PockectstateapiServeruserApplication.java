package com.pockectstate.api.pockectstateapi_serveruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务
@MapperScan("com.pockectstate.api.pockectstateapi_serveruser.dao")
@EnableEurekaClient//注册为服务

public class PockectstateapiServeruserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectstateapiServeruserApplication.class, args);
    }

}
