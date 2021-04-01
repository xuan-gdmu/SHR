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
    @ExcelProperty(index = 0)
    private String deptName;
    @ExcelProperty(index = 1)
    private String deptManager;
    @ExcelProperty(index = 2)
    private String postName;
}
