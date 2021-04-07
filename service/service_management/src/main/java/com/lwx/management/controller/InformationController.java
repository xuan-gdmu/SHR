package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.entity.vo.InformationVo;
import com.lwx.management.service.InformationService;
import com.lwx.servicebase.exceptionhandler.LWXException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/management/information")
@CrossOrigin
public class InformationController {
    @Autowired
    private InformationService informationService;


    @ApiOperation(value = "列表")
    @GetMapping("findAll")
    public MyResult findAllInformation(){
        List<Information> list = informationService.list(null);
        return MyResult.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除信息")
    @DeleteMapping("{id}")
    public MyResult removeInformation(@ApiParam(name = "id", value = "ID", required = true)
                           @PathVariable String id) {
        boolean flag = informationService.removeById(id);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("pageInformation/{current}/{limit}")
    public MyResult pageListInformation(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<Information> informationPage = new Page<>(current,limit);

        try {
            int i = 10/0;
        }catch(Exception e) {
            //执行自定义异常
            throw new LWXException(20001,"执行了自定义异常处理....");
        }


        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        informationService.page(informationPage,null);
        //总记录数
        long total = informationPage.getTotal();
        //数据list集合
        List<Information> records = informationPage.getRecords();

        /*Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);*/

        return MyResult.ok().data("total",total).data("rows",records);
    }


    @PostMapping("pageInformationCondition/{current}/{limit}")
    public MyResult pageInformationCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) InformationQuery informationQuery) {
        //创建page对象
        Page<Information> informationPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = informationQuery.getName();
        String level = informationQuery.getStaffType();
        String begin = informationQuery.getBegin();
        String end = informationQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("staff_type",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("joinDate",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("joinDate",end);
        }

        //排序
        wrapper.orderByDesc("join_date");

        //调用方法实现条件查询分页
        informationService.page(informationPage,wrapper);
        //总记录数
        long total = informationPage.getTotal();
        //数据list集合
        List<Information> records = informationPage.getRecords();
        return MyResult.ok().data("total",total).data("rows",records);
    }

    @PostMapping("addInformation")
    public MyResult addInformation(@RequestBody InformationVo informationvo) {
        Information information = new Information();
        try{
            if(informationvo.getJoinDate() != null && informationvo.getBornDate() != null && informationvo.getGetPositionDate() != null){
                informationvo.setJoinDate(informationvo.getJoinDate().substring(0,10));
                informationvo.setGetPositionDate(informationvo.getGetPositionDate().substring(0,10));
                informationvo.setBornDate(informationvo.getBornDate().substring(0,10));
                information.setJoinDate((new SimpleDateFormat("yyyy-MM-dd")).parse(informationvo.getJoinDate()));
                information.setGetPositionDate(new SimpleDateFormat("yyyy-MM-dd").parse(informationvo.getGetPositionDate()));
                information.setBornDate(new SimpleDateFormat("yyyy-MM-dd").parse(informationvo.getBornDate()));
                BeanUtils.copyProperties(informationvo,information);
            }
            boolean save = informationService.save(information);
            if(save) {
                return MyResult.ok();
            } else {
                return MyResult.error();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return MyResult.error();
    }


    @GetMapping("getInformationById/{id}")
    public MyResult getInformationById(@PathVariable String id) {
        Information information = informationService.getById(id);
        return MyResult.ok().data("information",information);
    }


    @PostMapping("updateInformation")
    public MyResult updateInformation(@RequestBody InformationVo informationvo) {
        Information information = new Information();
        try{
            if(informationvo.getJoinDate() != null && informationvo.getBornDate() != null && informationvo.getGetPositionDate() != null){
                informationvo.setJoinDate(informationvo.getJoinDate().substring(0,10));
                informationvo.setGetPositionDate(informationvo.getGetPositionDate().substring(0,10));
                informationvo.setBornDate(informationvo.getBornDate().substring(0,10));
                information.setJoinDate((new SimpleDateFormat("yyyy-MM-dd")).parse(informationvo.getJoinDate()));
                information.setGetPositionDate(new SimpleDateFormat("yyyy-MM-dd").parse(informationvo.getGetPositionDate()));
                information.setBornDate(new SimpleDateFormat("yyyy-MM-dd").parse(informationvo.getBornDate()));
                BeanUtils.copyProperties(informationvo,information);
            }
            boolean flag = informationService.updateById(information);
            if(flag) {
                return MyResult.ok();
            } else {
                return MyResult.error();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return MyResult.error();


    }
}

