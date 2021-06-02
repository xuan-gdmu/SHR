package com.lwx.vodservice.client;

import com.lwx.common.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("service-course") //调用的服务名称
@Component
public interface CourseVideoClient {

    @GetMapping("/course/video/getVideoInfoById/{id}")
    public MyResult getVideoInfoById(@PathVariable("id") String id);
}
