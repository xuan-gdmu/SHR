package com.lwx.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Contract;
import com.lwx.management.entity.Dismission;
import com.lwx.management.mapper.DismissionMapper;
import com.lwx.management.service.DismissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx
 * @since 2021-05-09
 */
@Service
public class DismissionServiceImpl extends ServiceImpl<DismissionMapper, Dismission> implements DismissionService {

    @Override
    public List<Dismission> getSelectDismissionPage(QueryWrapper<Dismission> queryWrapper, long current, long limit) {
        List<Dismission> dismissionList = baseMapper.getSelectDismission(queryWrapper, current, limit);
        return dismissionList;
    }
}
