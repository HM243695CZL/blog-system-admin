package com.hl.blog.modules.blog.dto;

import com.hl.blog.common.vo.PageParamsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BlogInfoPageDTO extends PageParamsDTO {

    @ApiModelProperty(value = "博客标题")
    private String title;

    @ApiModelProperty(value = "所属专栏")
    private String type;

    @ApiModelProperty(value = "标签")
    private String tags;
}
