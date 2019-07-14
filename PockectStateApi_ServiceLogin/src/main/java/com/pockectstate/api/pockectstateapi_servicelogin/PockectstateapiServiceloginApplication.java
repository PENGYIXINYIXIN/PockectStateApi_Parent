package com.pockectstate.api.pockectstateapi_servicelogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.pockectstate.api.pockectstateapi_servicelogin.dao")
@EnableEurekaClient

public class PockectstateapiServiceloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectstateapiServiceloginApplication.class, args);
    }

}
