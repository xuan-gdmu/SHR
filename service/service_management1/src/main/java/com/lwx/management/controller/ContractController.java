package com.lwx.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Contract;
import com.lwx.management.entity.Dismission;
import com.lwx.management.entity.Entry;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.ContractQuery;
import com.lwx.management.entity.vo.ContractVo;
import com.lwx.management.entity.vo.EntryVo;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.service.ContractService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/management/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    /**
     * 根据id进行查询
     */
    @GetMapping("{id}")
    public MyResult getContractById(@PathVariable String id) {
        Contract byId = contractService.getById(id);
        return MyResult.ok().data("contract",byId);
    }

//    @ApiOperation(value = "根据前端条件，分页查询返回数据")
//    @PostMapping("pageContractCondition/{current}/{limit}")
//    public MyResult pageContractCondition(@PathVariable long current, @PathVariable long limit,
//                                             @RequestBody(required = false) ContractQuery contractQuery) {
//        //创建page对象
//        Page<Contract> contractPage = new Page<>(current,limit);
//        //
//        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
//        // 多条件组合查询
//        // mybatis学过 动态sql
//        String name = contractQuery.getName();
//        String staffdept = contractQuery.getStaffdept();
//        String contstart = contractQuery.getContstart();
//        String contend = contractQuery.getContend();
//        //判断条件值是否为空，如果不为空拼接条件
//        if(!StringUtils.isEmpty(name)) {
//            //构建条件
//            wrapper.like("name",name);
//        }
//        if(!StringUtils.isEmpty(staffdept)) {
//            wrapper.eq("staffdept",staffdept);
//        }
//        if(!StringUtils.isEmpty(contstart)) {
//            wrapper.ge("contend",contstart);
//        }
//        if(!StringUtils.isEmpty(contend)) {
//            wrapper.le("contend",contend);
//        }
//        wrapper.eq("is_deleted", 0);
//        current = (current - 1) * 10;
//        //List<Contract> contractPageList = contractService.list(wrapper, current, limit);
//
//        //调用方法实现条件查询分页
//        contractService.page(contractPage,wrapper);
//
//        long total = contractPage.getTotal();//总记录数
//        List<Contract> contractPageList = contractPage.getRecords();//数据list集合
//        //限制10， list【0】、【1】、、、
//        //int count = contractService.count(wrapper);
//        //数据list集合
//        return MyResult.ok().data("total",total).data("rows",contractPageList);
//    }

    @PostMapping("pageContractCondition/{current}/{limit}")
    public MyResult pageContractCondition(@PathVariable long current,@PathVariable long limit,
                                       @RequestBody(required = false) ContractQuery contractQuery) {
        //创建page对象
        Page<Entry> contractPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = contractQuery.getName();
        String staffdept = contractQuery.getStaffdept();
        String contstart = contractQuery.getContstart();
        String contend = contractQuery.getContend();

        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(staffdept)) {
            wrapper.eq("staffdept",staffdept);
        }
        if(!StringUtils.isEmpty(contstart)) {
            wrapper.ge("contend",contstart);
        }
        if(!StringUtils.isEmpty(contend)) {
            wrapper.le("contend",contend);
        }
        wrapper.eq("is_delete", 0);
        current = (current - 1) * limit;
        List<Contract> contractList = contractService.getSelectContractPage(wrapper, current, limit);
        int count = contractService.count(wrapper);
        //数据list集合
        return MyResult.ok().data("total",count).data("rows", contractList);
    }

    //手动添加合同信息
    @PostMapping("addContract")
    public MyResult addContract(@RequestBody ContractVo contractVo){
        Contract contract = new Contract();
        contractVo.setContstart(contractVo.getContstart().substring(0,10));
        contractVo.setContend(contractVo.getContend().substring(0,10));
        try {
            contract.setContstart((new SimpleDateFormat("yyyy-MM-dd")).parse(contractVo.getContstart()));
            contract.setContend((new SimpleDateFormat("yyyy-MM-dd")).parse(contractVo.getContend()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BeanUtils.copyProperties(contractVo, contract);

        boolean save = contractService.save(contract);
        if(save) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //修改合同信息
    @PostMapping("updateContract")
    public MyResult updateContract(@RequestBody Contract contract) {
        boolean flag = contractService.updateById(contract);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    //删除合同信息的方法
    @ApiOperation(value = "逻辑删除岗位")
    @DeleteMapping("/deleteContractById/{id}")
    public MyResult removeById(@ApiParam(name = "id", value = "ID", required = true)
                               @PathVariable ArrayList<String> id) {
        boolean flag = false;
        for (String s : id) {
            flag = contractService.removeById(s);
        }
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @ApiOperation(value = "更新签订合同信息")
    @PostMapping("signContract/{id}")
    public MyResult signContract(@PathVariable String id) {
        Contract contract = contractService.getById(id);
        //if (contract.getConstate().equals("未签订") || contract.getConstate().equals("已结束")){
        if (contract.getConstate().equals("未签订")){
            contract.setConstate("已签订");
            contract.setContnum(contract.getContnum() + 1);
            contractService.updateById(contract);
            return MyResult.ok();
        }else if(contract.getConstate().equals("已签订") || contract.getConstate().equals("已结束")){
            return MyResult.error();
        }else {
            return MyResult.error();
        }
    }

    @ApiOperation(value = "更新结束合同信息")
    @PostMapping("closureContract/{id}")
    public MyResult closureContract(@PathVariable String id) {
        Contract contract = contractService.getById(id);
        if (contract.getConstate().equals("已签订")){
            contract.setConstate("已结束");
            contractService.updateById(contract);
            return MyResult.ok();
        }else if(contract.getConstate().equals("未签订") || contract.getConstate().equals("已结束")){
            return MyResult.error();
        }else {
            return MyResult.error();
        }
    }
}

