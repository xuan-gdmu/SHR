package com.lwx.statistics.client;

import com.lwx.common.MyResult;
import org.junit.Test;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-management")
public interface UcenterClient {
    /**
     * 查询一天的注册人数
     */
    @GetMapping("/management/information/countRegister/{day}")
    public MyResult countRegister(@PathVariable("day") String day);
}
