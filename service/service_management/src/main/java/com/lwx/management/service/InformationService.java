package com.lwx.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.management.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.management.entity.vo.InformationList;
import com.lwx.management.entity.vo.InformationQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
public interface InformationService extends IService<Information>   {
    List<Information> getInformationPageList(QueryWrapper<Information> queryWrapper, long current, long limit);
//    List<Information> getInformationPageList(long current, long limit, String name, String level);
//    List<InformationList> getInformationList(QueryWrapper<Information> queryWrapper, long current, long limit);
}
