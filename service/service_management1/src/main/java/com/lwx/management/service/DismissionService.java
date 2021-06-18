package com.lwx.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Contract;
import com.lwx.management.entity.Dismission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwx
 * @since 2021-05-09
 */
public interface DismissionService extends IService<Dismission> {
    List<Dismission> getSelectDismissionPage(QueryWrapper<Dismission> queryWrapper, long current, long limit);
}
