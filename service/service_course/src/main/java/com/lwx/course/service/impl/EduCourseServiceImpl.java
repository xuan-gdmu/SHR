package com.lwx.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.course.entity.EduCourse;
import com.lwx.course.entity.vo.CourseQuery;
import com.lwx.course.mapper.EduCourseMapper;
import com.lwx.course.service.EduChapterService;
import com.lwx.course.service.EduCourseService;
import com.lwx.course.service.EduVideoService;
import com.lwx.servicebase.exceptionhandler.LWXException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2021-05-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //注入小节和章节service
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService chapterService;

    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     * @return
     */
    @Override
    public EduCourse publishCourseInfo(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除课程(包括章节记录、视频记录)
     *
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) { //失败返回
            throw new LWXException(20001, "删除失败");
        }
    }

    /**
     * 分页查询，返回Map
     *
     * @param current
     * @param limit
     * @param courseQuery
     * @return
     */
    @Override
    public Map<String, Object> getPageCourse(long current, long limit, CourseQuery courseQuery) {
//        current = (current - 1) * 10;
        Page<EduCourse> eduCoursePage = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询,mybatis学过 动态sql
        String name = courseQuery.getCourseName();
        String station = courseQuery.getCourseStation();
        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("title", name);
        }
        if (!StringUtils.isEmpty(station)) {
            wrapper.eq("status", station);
        } else {
            wrapper.in("status", "Draft", "Normal");
        }
        wrapper.eq("is_deleted", 0);
        wrapper.orderByDesc("gmt_create");
        eduCourseService.page(eduCoursePage, wrapper);
        long total = eduCoursePage.getTotal();//总记录数
        List<EduCourse> records = eduCoursePage.getRecords(); //数据list集合

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return map;
    }


}
