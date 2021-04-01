package com.lwx.management.controller;


import com.lwx.common.MyResult;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class PostController {

    @Autowired
    private PostService postService;

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
    //删除部门的方法
    @ApiOperation(value = "逻辑删除岗位")
    @DeleteMapping("{id}")
    public MyResult removeDeptById(@ApiParam(name = "id", value = "岗位ID", required = true)
                                   @PathVariable String id) {
        boolean flag = postService.removeById(id);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //讲师修改功能
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

