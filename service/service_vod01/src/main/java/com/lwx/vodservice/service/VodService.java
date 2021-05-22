package com.lwx.vodservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.vodservice.entity.EduVideo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Administrator
 */
public interface VodService  {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List videoIdList);

}
