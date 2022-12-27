package com.hl.blog.modules.blog.dto;

import com.hl.blog.common.vo.PageParamsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BlogInfoGatewayDTO extends PageParamsDTO {

    @ApiModelProperty(value = "是否已发布")
    private Boolean State;

    @ApiModelProperty(value = "是否推荐")
    private Boolean isRecommend;

    @ApiModelProperty(value = "分类id")
    private String typeId;

    @ApiModelProperty(value = "标签id")
    private String tagId;
}
