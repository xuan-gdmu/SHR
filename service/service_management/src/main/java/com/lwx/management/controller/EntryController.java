package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Entry;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.Post;
import com.lwx.management.entity.vo.EntryVo;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.service.EntryService;
import com.lwx.management.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/management/entry")
@CrossOrigin
public class EntryController {
    @Autowired
    private EntryService entryService;


    /**
     * 根据id进行查询
     */
    @GetMapping("{id}")
    public MyResult getEntryById(@PathVariable String id) {
        Entry byId = entryService.getById(id);
        return MyResult.ok().data("entry",byId);
    }

    //手动添加
    @PostMapping("addEntry")
    public MyResult addEntry(@RequestBody EntryVo entryVo){
        Entry entry = new Entry();
        entryVo.setExpectedtime(entryVo.getExpectedtime().substring(0,10));
        try {
            entry.setExpectedtime((new SimpleDateFormat("yyyy-MM-dd")).parse(entryVo.getExpectedtime()));
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
        }

        BeanUtils.copyProperties(entryVo, entry);

        boolean save = entryService.save(entry);
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
            flag = entryService.removeById(id.get(i));
        }
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }


    @PostMapping("updateEntry")
    public MyResult updateEntry(@RequestBody Entry entry) {
        boolean flag = entryService.updateById(entry);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @PostMapping("pageEntryCondition/{current}/{limit}")
    //@RequestBody(required = false) InformationQuery informationQuery
    public MyResult pageEntryCondition(@PathVariable long current,@PathVariable long limit) {
        //创建page对象
        Page<Entry> entryPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Entry> wrapper = new QueryWrapper<>();
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

//        //调用方法实现条件查询分页
//        entryService.page(entryPage, wrapper);
//        //总记录数
//        long total = entryPage.getTotal();
//        //数据list集合
//        List<Entry> records = entryPage.getRecords();
        current = (current - 1) * 10;
        List<Entry> entryList = entryService.getSelectEntryPage(current, limit);
//        informationService.page(informationPage,wrapper);
//        long total = informationPageList.size();
        long total = entryService.count();
        int count = entryService.count(wrapper);
        //数据list集合
        List<Entry> records = entryList;
        return MyResult.ok().data("total",count).data("rows",records);
//        return MyResult.ok().data("total",total).data("rows",records);
    }
}

