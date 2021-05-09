package com.lwx.course.controller;

import com.lwx.common.MyResult;
import com.lwx.course.entity.EduCourse;
import com.lwx.course.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;



    //删除课程
    @DeleteMapping("{courseId}")
    public MyResult deleteCourse(@PathVariable String courseId) {
        courseService.removeById(courseId);
        return MyResult.ok();
    }

}

