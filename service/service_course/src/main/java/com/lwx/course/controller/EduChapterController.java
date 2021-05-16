package com.lwx.course.controller;



import com.lwx.common.MyResult;
import com.lwx.course.entity.EduChapter;
import com.lwx.course.entity.chapter.ChapterVo;
import com.lwx.course.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/course/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表,根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public MyResult getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return MyResult.ok().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("addChapter")
    public MyResult addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return MyResult.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public MyResult getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return MyResult.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public MyResult updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return MyResult.ok();
    }

    //删除的方法
    @DeleteMapping("{chapterId}")
    public MyResult deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }

    }
}


