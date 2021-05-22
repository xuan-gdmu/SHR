package com.lwx.vodservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.lwx.common.MyResult;
import com.lwx.servicebase.exceptionhandler.LWXException;
import com.lwx.vodservice.client.CourseVideoClient;
import com.lwx.vodservice.entity.EduVideo;
import com.lwx.vodservice.entity.InitObject;
import com.lwx.vodservice.service.VodService;
import com.lwx.vodservice.utils.ConstantVodUtils;
import com.lwx.vodservice.utils.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @Autowired
    private CourseVideoClient courseVideoClient;

    //上传视频到阿里云
    @PostMapping("uploadALiYunVideo")
    public MyResult uploadALiYunVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        System.out.println(videoId);
        return MyResult.ok().data("videoId", videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public MyResult removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return MyResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LWXException(90000, "删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public MyResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return MyResult.ok();
    }

    /**
     * 根据视频id获取视频播放地址
     *
     * @param id
     * @return
     */
    @GetMapping("getPlayURL/{id}")
    public MyResult getPlayURL(@PathVariable String id) {
//        try {
//            //创建初始化对象
//            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
//            //创建获取凭证request和response对象
//            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//            //向request设置视频id
//            request.setVideoId(id);
//            //调用方法得到凭证
//            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
//            String playAuth = response.getPlayAuth();
//        } catch (Exception e) {
//            throw new LWXException(90001, "获取凭证失败");
//        }
        try {
            DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取视频地址request和response
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            GetPlayInfoResponse response = new GetPlayInfoResponse();
            MyResult videoInfoById = courseVideoClient.getVideoInfoById(id);
            String videoSourceId = (String)videoInfoById.getData().get("videoSourceId");
            request.setVideoId(videoSourceId);
            response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            String URL = "";
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                URL = playInfo.getPlayURL();
            }
            System.out.println(URL);
            return MyResult.ok().data("URL",URL);
        }catch (Exception e){
            throw new LWXException(90002, "获取播放地址失败");
        }
    }


    //1 根据视频iD获取视频播放凭证
    public static void getPlayAuth() throws Exception{

        DefaultAcsClient client = InitObject.initVodClient("LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");

        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("85e4692836cf4d50bb564510273b6886");

        response = client.getAcsResponse(request);
        System.out.println("playAuth:"+response.getPlayAuth());
    }

    //1 根据视频iD获取视频播放地址
    public static void getPlayUrl() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");

        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request对象里面设置视频id
        request.setVideoId("474be24d43ad4f76af344b9f4daaabd1");

        //调用初始化对象里面的方法，传递request，获取数据

        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
