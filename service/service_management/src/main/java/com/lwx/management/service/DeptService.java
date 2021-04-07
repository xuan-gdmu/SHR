package com.lwx.management.service;

import com.lwx.management.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.management.entity.vo.DeptVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */

public interface DeptService extends IService<Dept> {



    void saveDept(MultipartFile file, DeptService deptService,PostService postService);

    List<DeptVo> deptVoList(PostService postService);
}
