package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.entity.vo.InformationVo;
import com.lwx.management.service.InformationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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


    @ApiOperation(value = "初代查询功能，查询所有员工")
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

    @ApiOperation(value = "根据前端条件，分页查询返回数据")
    @PostMapping("pageInformationCondition/{current}/{limit}")
    public MyResult pageInformationCondition(@PathVariable long current,@PathVariable long limit,
                                             @RequestBody(required = false) InformationQuery informationQuery) {
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
            wrapper.ge("join_date",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("join_date",end);
        }
        wrapper.eq("is_delete", 0);
        current = (current - 1) * 10;
        List<Information> informationPageList = informationService.getInformationPageList(wrapper, current, limit);
        int count = informationService.count(wrapper);
        //数据list集合
        return MyResult.ok().data("total",count).data("rows",informationPageList);
    }

    @ApiOperation(value = "新增员工档案")
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
            return MyResult.error().message("请规范填写所有必填项");
        }
    }

    @ApiOperation(value = "通过员工ID获取员工详细信息")
    @GetMapping("getInformationById/{id}")
    public MyResult getInformationById(@PathVariable String id) {
        Information information = informationService.getById(id);
        return MyResult.ok().data("information",information);
    }

    @ApiOperation(value = "更新员工详细信息")
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

    @ApiOperation(value = "更新员工详细信息")
    @PostMapping("informationDimission/{id}")
    public MyResult informationDimission(@PathVariable String id) {
        Information information = informationService.getById(id);
        if (information.getStaffStatus() != "离职"){
            information.setStaffStatus("离职");
            informationService.updateById(information);
            return MyResult.ok();
        }else{
            return MyResult.error();
        }
    }
}

