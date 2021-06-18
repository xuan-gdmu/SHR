package com.lwx.management.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lwx
 * @Create 2021-03-30-20:21
 * @Version V
 */
@Data
public class InformationQuery {
    @ApiModelProperty(value = "模糊查询")
    private String name;

    @ApiModelProperty(value = "type")
    private String staffType;

    @ApiModelProperty(value = "dept")
    private String belongDept;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
