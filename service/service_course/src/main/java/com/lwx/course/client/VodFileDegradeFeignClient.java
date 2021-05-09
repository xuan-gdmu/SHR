package com.lwx.course.client;

import com.lwx.common.MyResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
   //出错之后会执行
    @Override
    public MyResult removeAlyVideo(String id) {
        return MyResult.error().message("删除视频出错了");
    }

    @Override
    public MyResult deleteBatch(List<String> videoIdList) {
        return MyResult.error().message("删除多个视频出错了");
    }
}
