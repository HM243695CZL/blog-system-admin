package com.hl.blog.modules.blog.dto;

import com.hl.blog.common.vo.PageParamsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TagPageDTO extends PageParamsDTO {

    @ApiModelProperty(value = "标签名称")
    private String name;
}
