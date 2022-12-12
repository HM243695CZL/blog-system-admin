package com.hl.blog.modules.blog.model;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_type")
@ApiModel(value="BlogType对象", description="")
public class BlogType implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "专栏名称")
    @TableField("`name`")
    @NotBlank(message = "专栏名称不能为空")
    private String name;

    @ApiModelProperty(value = "专栏简介")
    @TableField("`desc`")
    private String desc;

    @ApiModelProperty(value = "博客数量")
    private Integer number;

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
