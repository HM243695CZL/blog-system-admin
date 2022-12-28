package com.hl.blog.modules.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonIdDTO {

    @ApiModelProperty(value = "id")
    private String id;
}
