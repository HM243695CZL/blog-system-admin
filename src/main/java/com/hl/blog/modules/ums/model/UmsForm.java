package com.hl.blog.modules.ums.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_form")
@ApiModel(value="UmsForm对象", description="")
public class UmsForm implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "表单名称")
    @NotBlank(message = "表单名称不能为空")
    @TableField("`name`")
    private String name;

    @ApiModelProperty(value = "表单key")
    @NotBlank(message = "表单key不能为空")
    private String formKey;

    @ApiModelProperty(value = "表单备注")
    private String remark;

    @ApiModelProperty(value = "表单配置")
    private String config;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
//    @TableLogic(value = "1", delval = "0")
    @JsonIgnore
    private Boolean deleted;


}
