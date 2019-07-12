package com.pockectstate.api.pockectstateapi_appapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient//发现服务
@EnableFeignClients//声明是服务消费
@EnableSwagger2//启用swagger扫描
public class PockectstateapiAppapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectstateapiAppapiApplication.class, args);
    }

}
