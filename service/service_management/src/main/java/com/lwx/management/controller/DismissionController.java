package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Contract;
import com.lwx.management.entity.Dismission;
import com.lwx.management.entity.Entry;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.ContractQuery;
import com.lwx.management.entity.vo.DismissionQuery;
import com.lwx.management.entity.vo.DismissionVo;
import com.lwx.management.service.DismissionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/management/dismission")
public class DismissionController {
    @Autowired
    private DismissionService dismissionService;


    /**
     * 根据id进行查询
     */
    @GetMapping("{id}")
    public MyResult getDismissionById(@PathVariable String id) {
        Dismission byId = dismissionService.getById(id);
        return MyResult.ok().data("dismission",byId);
    }

//    @PostMapping("pageDismissionCondition/{current}/{limit}")
//    public MyResult pageDismissionCondition(@PathVariable long current,@PathVariable long limit,
//                                          @RequestBody(required = false) DismissionQuery dismissionQuery) {
//        //创建page对象
//        Page<Dismission> dismissionPage = new Page<>(current,limit);
//        //构建条件
//        QueryWrapper<Dismission> wrapper = new QueryWrapper<>();
//        // 多条件组合查询
//        // mybatis学过 动态sql
//        String name = dismissionQuery.getName();
//        String dept = dismissionQuery.getDept();
//        String approvalmsg = dismissionQuery.getApprovalmsg();
//
//        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.isEmpty(name)) {
//            //构建条件
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(dept)) {
//            wrapper.eq("dept",dept);
//        }
//        if(!StringUtils.isEmpty(approvalmsg)) {
//            wrapper.ge("approvalmsg",approvalmsg);
//        }
////        if(!StringUtils.isEmpty(contend)) {
////            wrapper.le("contend",contend);
////        }
//        wrapper.eq("is_delete", 0);
//        current = (current - 1) * 3;
//        List<Dismission> dismissionList = dismissionService.getSelectDismissionPage(wrapper, current, limit);
//        int count = dismissionService.count(wrapper);
//        //数据list集合
//        List<Dismission> records = dismissionList;
//        return MyResult.ok().data("total",count).data("rows",records);
//    }

    @PostMapping("pageDismissionCondition/{current}/{limit}")
    public MyResult pageDismissionCondition(@PathVariable long current,@PathVariable long limit,
                                            @RequestBody(required = false) DismissionQuery dismissionQuery) {
        //创建page对象
        Page<Dismission> dismissionPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Dismission> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = dismissionQuery.getName();
        String dept = dismissionQuery.getDept();
        String approvalmsg = dismissionQuery.getApprovalmsg();

        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(dept)) {
            wrapper.eq("dept",dept);
        }
        if(!StringUtils.isEmpty(approvalmsg)) {
            wrapper.eq("approvalmsg",approvalmsg);
        }
//        if(!StringUtils.isEmpty(contend)) {
//            wrapper.le("contend",contend);
//        }
        wrapper.eq("is_delete", 0);
//        dismissionService.page(dismissionPage, wrapper);
//        //总记录数
//        long total = dismissionPage.getTotal();
//        //数据list集合
//        List<Dismission> records = dismissionPage.getRecords();
//        return MyResult.ok().data("total", total).data("rows", records);

        current = (current - 1) * limit;
        List<Dismission> dismissionList = dismissionService.getSelectDismissionPage(wrapper, current, limit);
        int count = dismissionService.count(wrapper);
        //数据list集合
        return MyResult.ok().data("total",count).data("rows", dismissionList);
    }


    //手动添加离职信息
    @PostMapping("addDismission")
    public MyResult addDismission(@RequestBody DismissionVo dismissionVo){
        Dismission dismission = new Dismission();
        dismissionVo.setEntrytime(dismissionVo.getEntrytime().substring(0,10));
        dismissionVo.setDismissiontime(dismissionVo.getDismissiontime().substring(0,10));
        try {
            dismission.setEntrytime((new SimpleDateFormat("yyyy-MM-dd")).parse(dismissionVo.getEntrytime()));
            dismission.setDismissiontime((new SimpleDateFormat("yyyy-MM-dd")).parse(dismissionVo.getDismissiontime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BeanUtils.copyProperties(dismissionVo, dismission);

        boolean save = dismissionService.save(dismission);
        if(save) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //修改离职信息
    @PostMapping("updateDismisssion")
    public MyResult updateDismisssion(@RequestBody Dismission dismission) {
        boolean flag = dismissionService.updateById(dismission);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
    //删除离职信息的方法
    @ApiOperation(value = "逻辑删除岗位")
    @DeleteMapping("/deleteDismissionById/{id}")
    public MyResult removeById(@ApiParam(name = "id", value = "ID", required = true)
                               @PathVariable ArrayList<String> id) {
        boolean flag = false;
        for (String s : id) {
            flag = dismissionService.removeById(s);
        }
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @ApiOperation(value = "更新离职审批信息")
    @PostMapping("appmsgDismission/{id}")
    public MyResult appmsgDismission(@PathVariable String id) {
        Dismission dismission = dismissionService.getById(id);
        if (dismission.getApprovalmsg().equals("待审批")){
            dismission.setApprovalmsg("已通过");
            dismissionService.updateById(dismission);
            return MyResult.ok();
        }else if(dismission.getApprovalmsg().equals("已否决") || dismission.getApprovalmsg().equals("已通过")){
            return MyResult.error();
        }else {
            return MyResult.error();
        }
    }

    @ApiOperation(value = "更新离职审批信息")
    @PostMapping("appmsgRefuse/{id}")
    public MyResult appmsgRefuse(@PathVariable String id) {
        Dismission dismission = dismissionService.getById(id);
        if (dismission.getApprovalmsg().equals("待审批")){
            dismission.setApprovalmsg("已否决");
            dismissionService.updateById(dismission);
            return MyResult.ok();
        }else if(dismission.getApprovalmsg().equals("已否决") && dismission.getApprovalmsg().equals("已通过")){
            return MyResult.error();
        }else {
            return MyResult.error();
        }
    }
}

