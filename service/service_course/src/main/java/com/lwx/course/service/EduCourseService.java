package com.lwx.course.service;

import com.lwx.course.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.course.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lwx
 * @since 2021-05-16
 */
public interface EduCourseService extends IService<EduCourse> {

    EduCourse publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String,List> getPageCourse(long current, long limit, CourseQuery courseQuery);

}
