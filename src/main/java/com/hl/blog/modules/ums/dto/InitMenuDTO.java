package com.hl.blog.modules.ums.dto;

import lombok.Data;

import java.util.List;

/**
 * 初始化菜单的数据传输对象
 */
@Data
public class InitMenuDTO {

    private Integer id;

    private Integer pid;

    private String path;

    private String name;

    private String component;

    private MenuMataDTO meta;

    private List<InitMenuDTO> children;
}

