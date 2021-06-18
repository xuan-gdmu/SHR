package com.lwx.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author lwx
 * @Create 2021-03-30-19:53
 * @Version V
 */
@EnableSwagger2
@ComponentScan(basePackages = {"com.lwx"})
@SpringBootApplication
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication. class,args);
    }
}
