package com.lwx.management.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author lwx
 * @Create 2021-04-01-10:28
 * @Version V
 */
@Data
public class DeptData {
    @ExcelProperty(value = "部门名称",index = 0)
    private String deptName;
    @ExcelProperty(value = "部门经理名称",index = 1)
    private String deptManager;
    @ExcelProperty(value = "岗位名称",index = 2)
    private String postName;
}
