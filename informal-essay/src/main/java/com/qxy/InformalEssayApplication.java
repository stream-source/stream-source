package com.qxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class InformalEssayApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformalEssayApplication.class, args);
    }


}
