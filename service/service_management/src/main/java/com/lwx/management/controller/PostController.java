package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/management/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;


    /** 根据岗位id进行查询
     */
    @GetMapping("{id}")
    public MyResult getPostById(@PathVariable String id) {
        Post post = postService.getById(id);
        return MyResult.ok().data("post",post);
    }

    /**
     * 根据部门id外键查找相应岗位
     * @param id
     * @return
     */
    @GetMapping("/postList/{id}")
    public MyResult getPostByPdeptno(@PathVariable String id) {
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        postQueryWrapper.eq("pdeptno",id);
        List<Post> postList = postService.list(postQueryWrapper);
        return MyResult.ok().data("post",postList);
    }
    //手动添加公司部门
    @PostMapping("addPost")
    public MyResult addPost(@RequestBody Post post){
        boolean save = postService.save(post);
        if(save) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
    //删除岗位的方法
    @ApiOperation(value = "逻辑删除岗位")
    @DeleteMapping("/deleteById/{id}")
    public MyResult removeDeptById(@ApiParam(name = "id", value = "岗位ID", required = true)
                                   @PathVariable ArrayList<String> id) {
        boolean flag = false;
        for (int i = 0; i < id.size(); i++) {
            flag = postService.removeById(id.get(i));
        }
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }


    @PostMapping("updatePost")
    public MyResult updatePost(@RequestBody Post post) {
        boolean flag = postService.updateById(post);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
}

