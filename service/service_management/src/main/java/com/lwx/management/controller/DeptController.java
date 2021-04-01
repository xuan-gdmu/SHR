package com.lwx.management.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.entity.vo.DeptVo;
import com.lwx.management.service.DeptService;
import com.lwx.management.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

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

    //获取所有组织架构，用于显示
    @GetMapping("getList")
    public MyResult getList(){
        List<DeptVo> deptVos = deptService.deptVoList();
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
                           @PathVariable String id) {
        boolean flag = deptService.removeById(id);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //讲师修改功能
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

