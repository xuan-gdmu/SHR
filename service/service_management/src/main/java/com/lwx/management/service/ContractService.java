package com.lwx.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Contract;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.management.entity.Information;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwx
 * @since 2021-05-06
 */
public interface ContractService extends IService<Contract> {
    List<Contract> getSelectContractPage(QueryWrapper<Contract> queryWrapper, long current, long limit);

}
