package com.lwx.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.management.entity.Contract;
import com.lwx.management.entity.Information;
import com.lwx.management.mapper.ContractMapper;
import com.lwx.management.service.ContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.management.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2021-05-06
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {


    @Override
    public List<Contract> getSelectContractPage(QueryWrapper<Contract> queryWrapper, long current, long limit) {
        return baseMapper.getSelectContract(queryWrapper, current, limit);
    }
}
