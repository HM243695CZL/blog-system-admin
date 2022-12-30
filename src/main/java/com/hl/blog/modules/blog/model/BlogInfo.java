package com.hl.blog.modules.blog.model;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 博客详情表
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("blog_info")
@ApiModel(value="BlogInfo对象", description="博客详情表")
public class BlogInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "博客标题")
    @NotBlank(message = "博客标题不能为空")
    private String title;

    @ApiModelProperty(value = "博客摘要")
    private String summary;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "所属专栏")
    private Integer type;

    @ApiModelProperty(value = "专栏名称")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "标签")
    private Integer tags;

    @ApiModelProperty(value = "标签名称")
    @TableField(exist = false)
    private String tagsName;

    @ApiModelProperty(value = "评论")
    private String comments;

    @ApiModelProperty(value = "首图地址")
    private String pictureUrl;

    @ApiModelProperty(value = "是否开启推荐")
    private Boolean isRecommend;

    @ApiModelProperty(value = "是否开启转载声明")
    private Boolean isReprint;

    @ApiModelProperty(value = "是否开启赞赏")
    private Boolean isAppreciation;

    @ApiModelProperty(value = "是否开启评论")
    private Boolean isComment;

    @ApiModelProperty(value = "1.原创; 2.转载; 3.翻译")
    private Integer property;

    @ApiModelProperty(value = "是否发布")
    private Boolean state;

    @ApiModelProperty(value = "上一个")
    @TableField(exist = false)
    private Integer prevId;

    @ApiModelProperty(value = "下一个")
    @TableField(exist = false)
    private Integer nextId;

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
