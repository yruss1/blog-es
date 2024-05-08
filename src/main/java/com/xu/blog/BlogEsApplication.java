package com.xu.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 11582
 */
@SpringBootApplication(scanBasePackages="com.xu.blog.*")
public class BlogEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogEsApplication.class, args);
//        IMServer.start();
    }

}
