package com.lwx.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.InformationList;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.mapper.InformationMapper;
import com.lwx.management.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {

    @Autowired
    private InformationService informationService;
//
//    public List<Information> getInformationPageList(QueryWrapper<Information> queryWrapper, long current, long limit){
//        List<Information> informationList = baseMapper.getInformationList(queryWrapper, current, limit);
//        return informationList;
//    }


    @Override
    public List<Information> getInformationPageList(QueryWrapper<Information> queryWrapper, long current, long limit) {
        List<Information> informationList = baseMapper.getInformationList(queryWrapper, current, limit);
        return informationList;
    }

//    @Override
//    public List<Information> getInformationPageList(long current, long limit, String name, String level) {
//        List<Information> informationList = baseMapper.getInformationList(current, limit, name, level);
//        return informationList;
//    }
}
