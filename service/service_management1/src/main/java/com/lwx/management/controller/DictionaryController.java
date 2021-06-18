package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dictionary;

import com.lwx.management.entity.vo.DictionaryQuery;
import com.lwx.management.entity.vo.DictionaryVo;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.service.DictionaryService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-04-11
 */
@RestController
@RequestMapping("/management/dictionary")
public class DictionaryController {
    private final DictionaryVo dictionaryVo = DictionaryVo.getInstance();

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("getSelections")
    public MyResult getSelections() {
        if(dictionaryVo.getStaffType() ==null){
            dictionaryVo.refreshList(dictionaryService);
        }
        return MyResult.ok().data("staffType", dictionaryVo.getStaffType())
                .data("staffStatus", dictionaryVo.getStaffStatus())
                .data("bank", dictionaryVo.getBank())
                .data("documentType", dictionaryVo.getDocumentType())
                .data("politicalStatus", dictionaryVo.getPoliticalStatus())
                .data("probation", dictionaryVo.getProbation())
                .data("education", dictionaryVo.getEducation());
    }

    @PostMapping("addDictionary")
    public MyResult addDictionary(@RequestBody Dictionary dictionary) {
        boolean save = dictionaryService.save(dictionary);
        if (save) {
            dictionaryVo.refreshList(dictionaryService);
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @PostMapping("pageDictionary/{current}/{limit}")
    public MyResult pageDictionary(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) DictionaryQuery dictionaryQuery) {
        //创建page对象
        Page<Dictionary> dictionaryPage = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String dicName = dictionaryQuery.getDicName();
        String indexNameCn = dictionaryQuery.getIndexNameCn();

        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(dicName)) {
            //构建条件
            wrapper.like("dic_name", dicName);
        }
        if (!StringUtils.isEmpty(indexNameCn)) {
            wrapper.like("index_name_cn", indexNameCn);
        }

        //排序
        wrapper.orderByAsc("dic_name");
        wrapper.orderByAsc("code_index");

        //调用方法实现条件查询分页
        dictionaryService.page(dictionaryPage, wrapper);
        //总记录数
        long total = dictionaryPage.getTotal();
        //数据list集合
        List<Dictionary> records = dictionaryPage.getRecords();
        return MyResult.ok().data("total", total).data("rows", records);
    }

    @DeleteMapping("{id}")
    public MyResult deleteById(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String id) {
        boolean flag = dictionaryService.removeById(id);
        if (flag) {
            dictionaryVo.refreshList(dictionaryService);
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @GetMapping("getById/{id}")
    public MyResult getById(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String id) {
        return MyResult.ok().data("dictionary",dictionaryService.getById(id));
    }

    @PostMapping("updateById")
    public MyResult updateById(@ApiParam(name = "id", value = "ID", required = true) @RequestBody Dictionary dictionary) {
        if (dictionaryService.updateById(dictionary)){
            return MyResult.ok();
        }else {
            return MyResult.error();
        }
    }
}

