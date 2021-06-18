package com.lwx.management.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.management.entity.Dismission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.management.entity.Entry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2021-05-09
 */
public interface DismissionMapper extends BaseMapper<Dismission> {
    List<Dismission> getSelectDismission(@Param(Constants.WRAPPER) QueryWrapper wrapper, @Param("current") long current, @Param("limit") long limit);
}
