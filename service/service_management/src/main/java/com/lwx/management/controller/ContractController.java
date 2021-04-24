package com.lwx.management.controller;


import com.lwx.common.MyResult;
import com.lwx.management.entity.Contract;
import com.lwx.management.entity.Post;
import com.lwx.management.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/management/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    //手动添加公司部门
    @PostMapping("addPost")
    public MyResult addPost(@RequestBody Contract contract){
        boolean save = contractService.save(contract);
        if(save) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @PostMapping("updatePost")
    public MyResult updatePost(@RequestBody Contract contract) {
        boolean flag = contractService.updateById(contract);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }
}

