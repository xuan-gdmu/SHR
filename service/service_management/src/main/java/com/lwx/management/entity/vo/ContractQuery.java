package com.lwx.management.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwx
 * @since 2021-05-06
 */
@Data
public class ContractQuery {

    private String name;

    private String staffdept;

    private String staffpost;

    private Long contid;

    private String conttype;

    private String constate;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String contstart;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String contend;

    private Integer contnum;



}
