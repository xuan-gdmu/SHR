package com.lwx.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Entry;
import com.lwx.management.mapper.EntryMapper;
import com.lwx.management.service.EntryService;
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
public class EntryServiceImpl extends ServiceImpl<EntryMapper, Entry> implements EntryService {


    @Override
    public List<Entry> getSelectEntryPage(QueryWrapper<Entry> wrapper, long current, long limit) {
        List<Entry> entryList = baseMapper.getSelectEntry(wrapper, current, limit);
        return entryList;
    }
}
