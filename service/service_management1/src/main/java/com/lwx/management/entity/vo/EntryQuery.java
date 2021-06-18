package com.lwx.management.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EntryQuery {

    private String name;

    private String employeeId;

    private String entrydept;

    private String entrypost;
}
