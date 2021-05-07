package com.lwx.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author lwx
 * @Email 1040152310@qq.com
 * @Create 2021-04-07-10:08
 * @Version Version1.0
 */
@SpringBootApplication
@ComponentScan({"com.lwx"})
@MapperScan("com.lwx.ucenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication. class,args);
    }
}
