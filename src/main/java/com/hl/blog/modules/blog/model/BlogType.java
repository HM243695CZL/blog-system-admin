package com.hl.blog.modules.blog.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String name;

    @ApiModelProperty(value = "专栏简介")
    private String desc;

    @ApiModelProperty(value = "博客数量")
    private Integer number;

    @ApiModelProperty(value = "创建时间")
    private Date addTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean delete;


}
