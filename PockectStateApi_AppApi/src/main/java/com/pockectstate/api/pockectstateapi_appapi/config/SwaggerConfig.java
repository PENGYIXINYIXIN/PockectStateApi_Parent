package com.pockectstate.api.pockectstateapi_appapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@Author PENGYIXIN
 *@Date Created in 2019/7/8 16:08
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo createAI(){
        return new ApiInfoBuilder().title("兜儿帮app接口平台").description("基于springboot+springcloud实现的微服务架构 提供兜儿帮APP的所有接口").version("1.0")
                .contact(new Contact("Feri","http://111","xingfei@163.com")).build();
    }
    @Bean
    public Docket createD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select().apis
                (RequestHandlerSelectors.basePackage("com.pockectstate.api.pockectstateapi_appapi.api")).build();
    }
}
