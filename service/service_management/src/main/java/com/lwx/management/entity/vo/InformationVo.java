package com.lwx.management.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lwx
 * @Email 1040152310@qq.com
 * @Create 2021-04-06-12:55
 * @Description TODO
 * @Version Version1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Information对象", description="")
public class InformationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private String phoneno;

    private String deptno;

    private String postno;


    private String email;

    private String location;

    private String remark;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String joinDate;

    private String staffType;

    private String staffStatus;

    private String probation;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String getPositionDate;

    private String documentType;

    private String identity;

    private String sex;

    //    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String bornDate;

    private Integer age;

    private String birthPlace;

    private String nationality;

    private String ethnicGroups;

    private Boolean marry;

    private Boolean hasBorn;

    private String politicCountenance;

    private String liveIn;

    private Long socialSecurityCard;

    private Long housingAccumulationFund;

    private Long accountNumber;

    private String bankName;

    private String bankLocation;

    private String emergencyContact;

    private String emergencyPhone;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    private String avatar;

    private String employeeId;


}

