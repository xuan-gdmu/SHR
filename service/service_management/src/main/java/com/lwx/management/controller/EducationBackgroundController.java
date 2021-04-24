package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.EducationBackground;
import com.lwx.management.entity.vo.DeptVo;
import com.lwx.management.service.EducationBackgroundService;
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
@RequestMapping("/management/education-background")
public class EducationBackgroundController {
//    @Autowired
//    private EducationBackgroundService educationBackgroundService;
//
//    @GetMapping("getEdu")
//    public MyResult getEdu(String id){
//        QueryWrapper<EducationBackground> educationBackgroundQueryWrapper = new QueryWrapper<>();
//        educationBackgroundQueryWrapper.eq("id",id);
//        EducationBackground educationBackgroundOne = educationBackgroundService.getOne(educationBackgroundQueryWrapper);
//        return MyResult.ok().data("data",educationBackgroundOne);
//    }
//
//
//    @GetMapping("getEduID")
//    public MyResult getEduID(){
//        educationBackgroundService.
//        return MyResult.ok().data("items", deptVos);
//    }
//
//    //手动添加公司部门
//    @PostMapping("addEdu")
//    public MyResult addNewDept(@RequestBody Dept dept){
//        boolean save = deptService.save(dept);
//        if(save) {
//            return MyResult.ok();
//        } else {
//            return MyResult.error();
//        }
//    }
//    //删除部门的方法
//    @ApiOperation(value = "逻辑删除教育背景")
//    @DeleteMapping("{id}")
//    public MyResult removeDeptById(@ApiParam(name = "id", value = "教育背景ID", required = true)
//                                   @PathVariable ArrayList<String> id) {
//        boolean flag = false;
//        for(int i = 0;i < id.size();i++){
//            flag = deptService.removeById(id.get(i));
//        }
//        if(flag) {
//            return MyResult.ok();
//        } else {
//            return MyResult.error();
//        }
//    }
//
//    //修改功能
//    @PostMapping("updateEdu")
//    public MyResult updateDept(@RequestBody Dept dept) {
//        boolean flag = deptService.updateById(dept);
//        if(flag) {
//            return MyResult.ok();
//        } else {
//            return MyResult.error();
//        }
//    }
//
//
}

