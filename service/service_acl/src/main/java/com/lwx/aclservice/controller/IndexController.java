package com.lwx.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwx.aclservice.service.IndexService;
import com.lwx.common.MyResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public MyResult info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return MyResult.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return MyResult.ok().data("permissionList", permissionList);
     */
    @GetMapping("menu")
    public MyResult getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return MyResult.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public MyResult logout(){
        return MyResult.ok();
    }

}
