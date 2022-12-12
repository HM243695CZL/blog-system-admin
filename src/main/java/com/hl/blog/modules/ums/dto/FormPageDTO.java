package com.hl.blog.modules.ums.dto;

import com.hl.blog.common.vo.PageParamsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FormPageDTO extends PageParamsDTO {

    @ApiModelProperty(value = "表单名称")
    private String name;

    @ApiModelProperty(value = "表单key")
    private String formKey;

    @ApiModelProperty(value = "表单备注")
    private String remark;
}
