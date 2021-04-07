package com.lwx.oss.controller;

import com.lwx.common.MyResult;
import com.lwx.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/managementoss/fileoss")
@CrossOrigin(methods = {RequestMethod.POST})
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    public MyResult uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return MyResult.ok().data("url",url);
    }
}
