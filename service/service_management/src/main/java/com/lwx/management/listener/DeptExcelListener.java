package com.lwx.management.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.entity.excel.DeptData;
import com.lwx.management.service.DeptService;
import com.lwx.management.service.PostService;
import com.lwx.servicebase.exceptionhandler.LWXException;


/**
 * @author Administrator
 */
public class DeptExcelListener extends AnalysisEventListener<DeptData> {

    public DeptService deptService;
    public PostService postService;
    public DeptExcelListener() {}
    public DeptExcelListener(DeptService deptService,PostService postService) {
        this.deptService = deptService;
        this.postService = postService;
    }

    @Override
    public void invoke(DeptData deptData, AnalysisContext analysisContext) {

        if(deptData == null) {
            throw new LWXException(20001,"文件数据为空");
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        Dept existDept = this.existDept(deptService, deptData.getDeptName());
        //没有相同一级分类，进行添加
        if(existDept == null) {
            existDept = new Dept();
            //一级分类名称
            existDept.setDname(deptData.getDeptName());
            existDept.setDmanager(deptData.getDeptManager());
            deptService.save(existDept);
        }

        //获取一级分类id值
        String pid = existDept.getId();

        //添加二级分类
        //判断二级分类是否重复
        Post existTwoSubject = this.existPost(postService, deptData.getPostName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new Post();
            existTwoSubject.setPdeptno(pid);
            //二级分类名称
            existTwoSubject.setDname(deptData.getPostName());
            postService.save(existTwoSubject);
        }
    }

    //判断部门不能重复添加
    private Dept existDept(DeptService deptService,String name) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("dname",name);
        Dept oneDept = deptService.getOne(wrapper);
        return oneDept;
    }

    //判断岗位不能重复添加
    private Post existPost(PostService postService, String name, String pid) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("dname",name);
        wrapper.eq("pdeptno",pid);
        Post post = postService.getOne(wrapper);
        return post;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

