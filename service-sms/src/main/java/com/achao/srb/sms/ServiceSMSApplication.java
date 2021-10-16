package com.achao.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.achao.srb","com.achao.common"})
@EnableFeignClients
public class ServiceSMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSMSApplication.class, args);
    }
}