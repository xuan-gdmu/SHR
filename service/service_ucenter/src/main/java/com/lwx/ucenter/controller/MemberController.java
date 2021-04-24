package com.lwx.ucenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 登出
     * @return  成功
     *
     */
    @PostMapping("logout")
    public MyResult logout() {
        return MyResult.ok();
    }

    /** 分页查询
     *
     * @param current
     * @param limit
     * @param member
     * @return 分页查询List列表
     *
     */
//    @PostMapping("pageUserCondition/{current}/{limit}")
//    public MyResult pageInformationCondition(@PathVariable long current,@PathVariable long limit,
//                                  @RequestBody(required = false) Member member) {
//        //创建page对象
//        Page<Member> memberPage = new Page<>(current,limit);
//
//        //构建条件
//        QueryWrapper<Member> wrapper = new QueryWrapper<>();
//        // 多条件组合查询
//        // mybatis学过 动态sql
//        String name = member.getMobile();
//        String  sex = member.getSex().toString();
//        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.isEmpty(name)) {
//            //构建条件
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(sex)) {
//            wrapper.eq("sex",sex);
//        }
//
//        //排序
//        wrapper.orderByDesc("gmt_create");

//        //调用方法实现条件查询分页
//        memberService.page(memberPage,wrapper);
//        //总记录数
//        List<Member> informationPageList = memberService.li(wrapper, current, limit);
//        long total = informationPage.getTotal();
//        //数据list集合
//        List<Information> records = informationPageList;
//        return MyResult.ok().data("total",total).data("rows",records);
//    }
}

