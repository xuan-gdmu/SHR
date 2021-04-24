package com.lwx.management.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.management.entity.Entry;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.management.entity.Information;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
public interface EntryMapper extends BaseMapper<Entry> {
    List<Entry> getSelectEntry( @Param("current") long current, @Param("limit") long limit);

}
