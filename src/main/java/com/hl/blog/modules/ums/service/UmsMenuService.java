package com.hl.blog.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.blog.modules.ums.dto.InitMenuDTO;
import com.hl.blog.modules.ums.model.UmsMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-06-21
 */
public interface UmsMenuService extends IService<UmsMenu> {

    List<UmsMenu> getMenuList();

    UmsMenu view(String id);

    boolean delete(String id);

    // 根据用户id获取用户菜单
    List<InitMenuDTO> getMenuListByUserId(Integer userId);
}
