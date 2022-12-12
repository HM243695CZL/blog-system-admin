package com.hl.blog.modules.ums.dto;

import lombok.Data;

import java.util.List;

/**
 * 设置角色对应菜单权限的DTO
 */
@Data
public class AuthMenuDTO {

    // 菜单id数组
    private List<Integer> menuIds;

    // 角色id
    private Integer id;
}
