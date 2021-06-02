package com.lwx.management.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.management.entity.Contract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.management.entity.Information;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2021-05-06
 */
public interface ContractMapper extends BaseMapper<Contract> {
    List<Contract> getSelectContract(@Param(Constants.WRAPPER) QueryWrapper<Contract> queryWrapper, @Param("current") long current, @Param("limit") long limit);
}
