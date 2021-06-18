package com.lwx.management.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class InformationList {
    private String name;

    private String employeeId;

    private String deptName;

    private String postName;

    private String staffType;

    private String staffStatus;

    private String joinDate;

    private Date gmtCreate;

    private String remark;
}
