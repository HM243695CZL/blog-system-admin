package com.hl.blog.modules.blog.model;

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
 * 博客标签表
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_tag")
@ApiModel(value="BlogTag对象", description="博客标签表")
public class BlogTag implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标签名称")
    @TableField("`name`")
    @NotBlank(message = "标签名称不能为空")
    private String name;

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
    @TableLogic(value = "1", delval = "0")
    @JsonIgnore
    private Boolean deleted;


}
