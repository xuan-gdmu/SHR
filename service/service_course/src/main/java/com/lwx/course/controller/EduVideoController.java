package com.lwx.course.controller;


import com.lwx.common.MyResult;
import com.lwx.course.client.VodClient;
import com.lwx.course.entity.EduVideo;
import com.lwx.course.service.EduVideoService;
import com.lwx.servicebase.exceptionhandler.LWXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/course/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //注入vodClient
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public MyResult addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return MyResult.ok();
    }

    //删除小节，删除对应阿里云视频
    @DeleteMapping("{id}")
    public MyResult deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            MyResult result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new LWXException(20001,"删除视频失败，熔断器...");
            }
        }
        //删除小节
        videoService.removeById(id);
        return MyResult.ok();
    }

    @GetMapping("getVideoInfoById/{id}")
    public MyResult getVideoInfoById(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        return MyResult.ok().data("videoSourceId", videoSourceId);
    }
}


