package com.lwx.management.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.management.entity.Information;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwx.management.entity.vo.InformationList;
import com.lwx.management.entity.vo.InformationQuery;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */
public interface InformationMapper extends BaseMapper<Information> {

//    @Select("SELECT t.`name`  as   name,"+
//            "t.`employee_id`  as   employee_no,"+
//            "d.`dname`    as   department_name,"+
//            "p.`dname`    as   position_name,"+
//            "t.`phoneno`   as   phone ,"+
//            "t.`email`        as   email,"+
//            "t.`join_date`   as   join_date,"+
//            "t.`staff_type`   as   staff_type,"+
//            "t.`staff_status` as   staff_status,"+
//            "t.`remark`       as   remark"+
//            "FROM `information` t LEFT JOIN `dept` d  on t.`deptno` = d.`id`"+
//            "LEFT JOIN `post` p on t.`postno` = p.`id`"+
//            "${ew.customSqlSegment}"+
//            "LIMIT #{current}, #{limit}")
    List<Information> getInformationList(@Param(Constants.WRAPPER) QueryWrapper<Information> queryWrapper, @Param("current") long current, @Param("limit") long limit);

    Integer countRegisterDay(String day);
}