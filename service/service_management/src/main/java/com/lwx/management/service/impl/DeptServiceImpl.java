package com.lwx.management.service.impl;

import com.alibaba.excel.EasyExcel;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.excel.DeptData;
import com.lwx.management.entity.vo.DeptVo;
import com.lwx.management.listener.DeptExcelListener;
import com.lwx.management.mapper.DeptMapper;
import com.lwx.management.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.management.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
    public List<DeptVo> deptVoList() {
        return null;
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
