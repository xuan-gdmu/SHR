package com.lwx.vodservice;

/**
 * @Author lwx
 * @Create 2021-03-28-21:42
 * @Version V
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.lwx"})

public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication. class,args);
    }
}
