package com.lwx.management.controller;

import com.lwx.common.MyResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  //解决跨域
public class EduLoginController {
    //login
    @PostMapping("login")
    public MyResult login() {
        return MyResult.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public MyResult info() {
        return MyResult.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    @PostMapping("logout")
    public MyResult logout() {
        return MyResult.ok();
    }
}
