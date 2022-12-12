package com.hl.blog.modules.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FormKeyDTO {

    @ApiModelProperty(value = "表单的key")
    @NotBlank(message = "key不能为空")
    private String key;
}
