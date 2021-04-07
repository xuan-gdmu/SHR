package com.lwx.management.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.entity.excel.DeptData;
import com.lwx.management.entity.vo.DeptVo;
import com.lwx.management.entity.vo.PostVo;
import com.lwx.management.listener.DeptExcelListener;
import com.lwx.management.mapper.DeptMapper;
import com.lwx.management.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.management.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Override
    public List<DeptVo> deptVoList(PostService postService) {
        //最终要的到的数据列表
        ArrayList<DeptVo> DeptVoArrayList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("id");
        queryWrapper.orderByAsc("id");
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        List<Post> myAllPost = postService.getMyAllPost();
        //填充一级分类vo数据
        int count = depts.size();
        for (int i = 0; i < count; i++) {
            Dept dept = depts.get(i);
            //创建一级类别vo对象
            DeptVo deptVo = new DeptVo();
            BeanUtils.copyProperties(dept, deptVo);
            DeptVoArrayList.add(deptVo);
            //填充二级分类vo数据
            ArrayList<PostVo> postVoArrayList = new ArrayList<>();
            int count2 = myAllPost.size();
            for (int j = 0; j < count2; j++) {
                Post post = myAllPost.get(j);
                if (dept.getId().equals(post.getPdeptno())) {
                    //创建二级类别vo对象
                    PostVo postVo = new PostVo();
                    BeanUtils.copyProperties(post, postVo);
                    postVoArrayList.add(postVo);
                }
            }
            deptVo.setChildren(postVoArrayList);
        }
        return DeptVoArrayList;
    }

    @Override
    public void saveDept(MultipartFile file, DeptService deptService, PostService postService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, DeptData.class, new DeptExcelListener(deptService,postService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
