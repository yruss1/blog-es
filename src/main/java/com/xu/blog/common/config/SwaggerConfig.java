package com.xu.blog.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author 11582
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket docket(Environment environment) {

        //设置swagger 环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles 判断是否自己设定在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apilnfo())
                .enable(flag)
                .select()
                //RequestHandlerSelectors 配置要扫描的接口方式
                //basePackage: 指定要扫描的包
                // any() 扫描全部
                //none() 不扫描
                //withClassAnnotation() 扫描类上的注解
                //withMethodAnnotation() 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.xu.blog.controller"))
                //过滤
                //.paths(PathSelectors.ant(""))
                .build();
    }

    /**
     *  配置文档信息
     * @return ApiInfo
     */
    private ApiInfo apilnfo() {

        Contact contact = new Contact("russ", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger",
                "Swagger",
                "v1.0",
                "无",
                contact,
                "Apach 2.0 许可",
                "许可链接",
                new ArrayList<>()
        );
    }

}
