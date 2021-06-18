package com.lwx.management.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lwx
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Contract对象", description="")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    /** MP全局唯一ID策略，自动生成19位字符ID，用于唯一标识，无实际意义 */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String employeeId;

    private String name;

    private String staffdept;

    private String staffpost;

    private String contid;

    private String conttype;

    private String constate;

    private Integer conttimelimit;

    private Date contstart;

    private Date contend;

    private Integer contnum;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDelete;


}
