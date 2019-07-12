package com.pockectstate.api.pockectstateapi_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PockectstateapiEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectstateapiEurekaApplication.class, args);
    }

}
