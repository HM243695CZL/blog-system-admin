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
    private String title;

    @ApiModelProperty(value = "博客摘要")
    private String summary;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "所属专栏")
    private Integer type;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    @ApiModelProperty(value = "标签")
    private String tags;

    @ApiModelProperty(value = "评论")
    private String comments;

    @ApiModelProperty(value = "首图地址")
    private String pictureUrl;

    @ApiModelProperty(value = "是否开启推荐")
    private Integer isRecommend;

    @ApiModelProperty(value = "是否开启转载声明")
    private Integer isReprint;

    @ApiModelProperty(value = "是否开启赞赏")
    private Integer isAppreciation;

    @ApiModelProperty(value = "是否开启评论")
    private Integer isComment;

    @ApiModelProperty(value = "1.原创; 2.转载; 3.翻译")
    private Integer property;

    @ApiModelProperty(value = "状态: 0.草稿; 1: 已发布")
    private Integer state;

    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;


}
