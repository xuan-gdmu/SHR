package com.lwx.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.course.entity.EduCourse;
import com.lwx.course.entity.vo.CourseQuery;
import com.lwx.course.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/course/")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "根据前端条件，分页查询返回数据")
    @PostMapping("pageCourse/{current}/{limit}")
    public MyResult pageCourse(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) CourseQuery courseQuery) {
        Map<String,List> map = courseService.getPageCourse(current, limit, courseQuery);
        return MyResult.ok().data("total", map.get("total").get(0)).data("rows", map.get("records"));
    }

    //添加课程基本信息的方法
    @PostMapping("addCourse")
    public MyResult addCourseInfo(@RequestBody EduCourse eduCourse) {
        //返回添加之后课程id，为了后面添加大纲使用
        boolean flag = courseService.save(eduCourse);
        if (flag) {
            String id = eduCourse.getId();
            return MyResult.ok().data("courseId", id);
        } else {
            return MyResult.error();
        }
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public MyResult getCourseInfo(@PathVariable String courseId) {
        EduCourse eduCourse = courseService.getById(courseId);
        return MyResult.ok().data("eduCourse", eduCourse);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public MyResult updateCourseInfo(@RequestBody EduCourse eduCourse) {
        courseService.updateById(eduCourse);
        return MyResult.ok();
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public MyResult publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return MyResult.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public MyResult deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return MyResult.ok();
    }

}



