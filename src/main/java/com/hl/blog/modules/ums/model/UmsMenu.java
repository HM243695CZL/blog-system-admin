package com.hl.blog.modules.ums.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_menu")
@ApiModel(value="UmsMenu对象", description="菜单表")
public class UmsMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父级id")
    private Integer pid;

    @ApiModelProperty(value = "菜单地址")
    @NotBlank(message = "菜单地址不能为空")
    private String path;

    @ApiModelProperty(value = "组件名称")
    @NotBlank(message = "组件名称不能为空")
    private String name;

    @ApiModelProperty(value = "组件地址")
    @NotBlank(message = "组件地址不能为空")
    private String component;

    @ApiModelProperty(value = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String title;

    @ApiModelProperty(value = "超链接地址")
    private String isLink;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean isHide;

    @ApiModelProperty(value = "是否缓存组件")
    private Boolean isKeepAlive;

    @ApiModelProperty(value = "是否固定")
    private Boolean isAffix;

    @ApiModelProperty(value = "是否内嵌窗口")
    private Boolean isIframe;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "角色id列表")
    @TableField(exist = false)
    private List<Integer> roleIds;

    @ApiModelProperty(value = "角色key列表")
    @TableField(exist = false)
    private List<String> roles;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<UmsMenu> children;
}
