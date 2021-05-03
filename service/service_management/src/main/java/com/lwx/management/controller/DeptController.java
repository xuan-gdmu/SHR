package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.entity.vo.DeptVo;
import com.lwx.management.service.DeptService;
import com.lwx.management.service.PostService;
import com.lwx.servicebase.exceptionhandler.LWXException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/management/dept")
@CrossOrigin
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private PostService postService;
    //添加公司架构分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addDept")
    public MyResult addDept(MultipartFile file) {
        //上传过来excel文件
        deptService.saveDept(file,deptService, postService);
        return MyResult.ok();
    }

    @GetMapping("getDept")
    public MyResult getDept(){
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        deptQueryWrapper.orderByDesc("id");
        List<Dept> depts = deptService.list(deptQueryWrapper);
        return MyResult.ok().data("data",depts);
    }

    //获取所有组织架构，用于显示
    @GetMapping("getList")
    public MyResult getList(){
        List<DeptVo> deptVos = deptService.deptVoList(postService);
        return MyResult.ok().data("items", deptVos);
    }

    //手动添加公司部门
    @PostMapping("addNewDept")
    public MyResult addNewDept(@RequestBody Dept dept){
        boolean save = deptService.save(dept);
        if(save) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
    //删除部门的方法
    @ApiOperation(value = "逻辑删除部门")
    @DeleteMapping("{id}")
    public MyResult removeDeptById(@ApiParam(name = "id", value = "部门ID", required = true)
                           @PathVariable ArrayList<String> id) {
        boolean flag = false;
        for(int i = 0;i < id.size();i++){
            flag = deptService.removeById(id.get(i));
        }
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //修改功能
    @PostMapping("updateDept")
    public MyResult updateDept(@RequestBody Dept dept) {
        boolean flag = deptService.updateById(dept);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
}

