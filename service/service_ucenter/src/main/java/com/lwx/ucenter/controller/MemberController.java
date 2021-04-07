package com.lwx.ucenter.controller;


import com.lwx.common.MyResult;
import com.lwx.ucenter.entity.Member;
import com.lwx.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**登陆
     *
     * @param member
     * @return
     */
    @PostMapping("login")
    public MyResult loginUser(@RequestBody Member member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);

        return MyResult.ok().data("token",token);
    }

    /**注册
     *
     * @param registerVo
     * @return
     */
    @PostMapping("register")
    public MyResult registerUser(@RequestBody com.lwx.ucenter.entity.vo.RegisterVo registerVo) {
        memberService.register(registerVo);
        return MyResult.ok();

    }

    /**根据token获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("getMemberInfo")
    public MyResult getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = com.lwx.common.JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return MyResult.ok().data("userInfo",member);
    }
}

