package com.lwx.management.controller;

import com.lwx.common.MyResult;
import com.lwx.management.entity.Information;
import com.lwx.management.entity.vo.InformationQuery;
import com.lwx.management.entity.vo.InformationShow;
import com.lwx.management.entity.vo.InformationVo;
import com.lwx.management.service.InformationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/management/information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    @ApiOperation(value = "初代查询功能，查询所有员工")
    @GetMapping("findAll")
    public MyResult findAllInformation(){
        List<Information> list = informationService.list(null);
        return MyResult.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除信息")
    @DeleteMapping("{id}")
    public MyResult removeInformation(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String id) {
        boolean flag = informationService.removeByStaffId(id);
        if(flag) {
            return MyResult.ok();
        } else {
            return MyResult.error();
        }
    }

    @ApiOperation(value = "根据前端条件，分页查询返回数据")
    @PostMapping("pageInformationCondition/{current}/{limit}")
    public MyResult pageInformationCondition(@PathVariable long current,@PathVariable long limit, @RequestBody(required = false) InformationQuery informationQuery) {
        Map<String, List> map = informationService.getpageInformationCondition(current, limit, informationQuery);
        return MyResult.ok().data("total",map.get("total").get(0)).data("rows",map.get("records"));
    }

    @ApiOperation(value = "通过员工ID获取员工详细信息")
    @GetMapping("getInformationById/{id}")
    public MyResult getInformationById(@PathVariable String id) {
        InformationShow information = informationService.getInformationById(id);
        return MyResult.ok().data("information",information);
    }

    @ApiOperation(value = "新增员工档案")
    @PostMapping("addInformation")
    public MyResult addInformation(@RequestBody InformationVo informationvo) {
        boolean flag = informationService.addInformation(informationvo);
        if (flag){
            return MyResult.ok();
        }else {
            return MyResult.error();
        }

    }

    @ApiOperation(value = "更新员工详细信息")
    @PostMapping("updateInformation")
    public MyResult updateInformation(@RequestBody InformationVo informationvo) {
        boolean flag = informationService.updateInformation(informationvo);
        if (flag){
            return MyResult.ok();
        }else {
            return MyResult.error();
        }
    }

    @ApiOperation(value = "更新员工详细信息")
    @PostMapping("informationDimission/{id}")
    public MyResult informationDimission(@PathVariable String id) {
        Information information = informationService.getById(id);
        if (!information.getStaffStatus().equals("离职")){
            information.setStaffStatus("离职");
            informationService.updateById(information);
            return MyResult.ok();
        }else if(information.getStaffStatus().equals("离职")){
            return MyResult.error();
        }else {
            return MyResult.error();
        }
    }

    /**
     * 查询一天的注册人数
     */
    @GetMapping("countRegister/{day}")
    public MyResult countRegister(@PathVariable String day){
        Integer count = informationService.countRegisterDay(day);
        return MyResult.ok().data("countRegister", count);
    }

}

