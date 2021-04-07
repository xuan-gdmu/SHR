package com.lwx.management.service;

import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */

public interface PostService extends IService<Post> {
    List<Post> getMyAllPost() ;
}
