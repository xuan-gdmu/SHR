package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dimission;
import com.lwx.management.entity.Entry;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.service.DimissionService;
import com.lwx.management.service.EntryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/management/dimission")
public class DimissionController {
    @Autowired
    private DimissionService dimissionService;


    /**
     * 根据id进行查询
     */
    @GetMapping("{id}")
    public MyResult getDimissionById(@PathVariable String id) {
        Dimission byId = dimissionService.getById(id);
        return MyResult.ok().data("dimission",byId);
    }


    //手动添加
    @PostMapping("addDimission")
    public MyResult addDimission(@RequestBody Dimission dimission){
        boolean save = dimissionService.save(dimission);
        if(save) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
    //删除岗位的方法
    @ApiOperation(value = "逻辑删除岗位")
    @DeleteMapping("/deleteById/{id}")
    public MyResult removeById(@ApiParam(name = "id", value = "ID", required = true)
                               @PathVariable ArrayList<String> id) {
        boolean flag = false;
        for (int i = 0; i < id.size(); i++) {
            flag = dimissionService.removeById(id.get(i));
        }
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }


    @PostMapping("updateDimission")
    public MyResult updateEntry(@RequestBody Dimission dimission) {
        boolean flag = dimissionService.updateById(dimission);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @PostMapping("pageDimissionCondition/{current}/{limit}")
    public MyResult pageEntryCondition(@PathVariable long current,@PathVariable long limit,
                                       @RequestBody(required = false) InformationQuery informationQuery) {
        //创建page对象
        Page<Dimission> entryPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Dimission> wrapper = new QueryWrapper<>();
//        // 多条件组合查询
//        // mybatis学过 动态sql
//        String name = informationQuery.getName();
//        String level = informationQuery.getStaffType();
//        String begin = informationQuery.getBegin();
//        String end = informationQuery.getEnd();
//        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.isEmpty(name)) {
//            //构建条件
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(level)) {
//            wrapper.eq("staff_type",level);
//        }
//        if(!StringUtils.isEmpty(begin)) {
//            wrapper.ge("join_date",begin);
//        }
//        if(!StringUtils.isEmpty(end)) {
//            wrapper.le("join_date",end);
//        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        dimissionService.page(entryPage, wrapper);
        //总记录数
        long total = entryPage.getTotal();
        //数据list集合
        List<Dimission> records = entryPage.getRecords();
        return MyResult.ok().data("total",total).data("dimission",records);
    }
}

