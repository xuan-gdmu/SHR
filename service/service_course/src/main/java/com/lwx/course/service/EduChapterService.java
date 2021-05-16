package com.lwx.course.service;

import com.lwx.course.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.course.entity.chapter.ChapterVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lwx
 * @since 2021-05-16
 */
@Service
public interface EduChapterService extends IService<EduChapter> {

    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    //删除章节的方法
    boolean deleteChapter(String chapterId);

    //2 根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
