package com.lwx.management.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lwx
 * @Email 1040152310@qq.com
 * @Create 2021-04-13-14:54
 * @Version Version1.0
 */
@Data
public class DictionaryQuery {
    @ApiModelProperty(value = "模糊查询")
    private String dicName;

    @ApiModelProperty(value = "data")
    private String indexNameCn;


}
