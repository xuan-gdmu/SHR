package com.lwx.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Dept;
import com.lwx.management.entity.Post;
import com.lwx.management.mapper.PostMapper;
import com.lwx.management.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Override
    public List<Post> getMyAllPost(){
        QueryWrapper<Post> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.isNotNull("pdeptno");
        queryWrapper1.orderByDesc("id");
        List<Post> posts = baseMapper.selectList(queryWrapper1);
        return posts;
    }

}
