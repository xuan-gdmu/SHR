package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dictionary;

import com.lwx.management.entity.vo.DictionaryQuery;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.service.DictionaryService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-04-11
 */
@RestController
@RequestMapping("/management/dictionary")
@CrossOrigin
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("getEmployeeType")
    public MyResult getEmployeeType() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name","员工类型");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return MyResult.ok().data("dic",dictionaryList);
    }
    @GetMapping("getEmployeeStatus")
    public MyResult getInformationById() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name","员工状态");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return MyResult.ok().data("dic",dictionaryList);
    }
    @GetMapping("getProbation")
    public MyResult getProbation() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name","试用期");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return MyResult.ok().data("dic",dictionaryList);
    }
    @GetMapping("getDocumentType")
    public MyResult getDocumentType() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name","证件类型");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return MyResult.ok().data("dic",dictionaryList);
    }
    @GetMapping("getBank")
    public MyResult getBank() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name","开户银行");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return MyResult.ok().data("dic",dictionaryList);
    }
    @GetMapping("getPoliticalStatus")
    public MyResult getPoliticalStatus() {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name","政治面貌");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return MyResult.ok().data("dic",dictionaryList);
    }

    @PostMapping("addDictionary")
    public MyResult addDictionary(@RequestBody Dictionary dictionary) {
        boolean save = dictionaryService.save(dictionary);
        if(save){
            return MyResult.ok();
        }else {
            return MyResult.error();
        }
    }

    @PostMapping("pageDictionary/{current}/{limit}")
    public MyResult pageDictionary(@PathVariable long current,@PathVariable long limit,
                                   @RequestBody(required = false) DictionaryQuery dictionaryQuery) {
        //创建page对象
        Page<Dictionary> dictionaryPage = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String dicName = dictionaryQuery.getDicName();
        String indexNameCn = dictionaryQuery.getIndexNameCn();

        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(dicName)) {
            //构建条件
            wrapper.like("dic_name",dicName);
        }
        if(!StringUtils.isEmpty(indexNameCn)) {
            wrapper.like("index_name_cn",indexNameCn);
        }

        //排序
        wrapper.orderByAsc("dic_name");
        wrapper.orderByAsc("code_index");
//        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        dictionaryService.page(dictionaryPage,wrapper);
        //总记录数
        long total = dictionaryPage.getTotal();
        //数据list集合
        List<Dictionary> records = dictionaryPage.getRecords();
        return MyResult.ok().data("total",total).data("rows",records);
    }

    @DeleteMapping("{id}")
    public MyResult deleteById(@ApiParam(name = "id", value = "ID", required = true)
                               @PathVariable String id){
        boolean flag = dictionaryService.removeById(id);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }


}

