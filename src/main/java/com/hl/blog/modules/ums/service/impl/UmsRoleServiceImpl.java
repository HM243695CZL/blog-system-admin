package com.hl.blog.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.ums.dto.AuthMenuDTO;
import com.hl.blog.modules.ums.mapper.UmsRoleMapper;
import com.hl.blog.modules.ums.model.UmsAdminRole;
import com.hl.blog.modules.ums.model.UmsRole;
import com.hl.blog.modules.ums.model.UmsRoleMenu;
import com.hl.blog.modules.ums.service.UmsAdminRoleService;
import com.hl.blog.modules.ums.service.UmsRoleMenuService;
import com.hl.blog.modules.ums.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-06-19
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    UmsAdminRoleService adminRoleService;

    @Autowired
    UmsRoleMenuService roleMenuService;

    /**
     * 分页
     * @param paramsDTO
     * @return
     */
    @Override
    public Page<UmsRole> pageList(PageParamsDTO paramsDTO) {
        Page<UmsRole> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        return page(page, wrapper);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean delete(String id) {
        // 删除用户角色表的角色
        QueryWrapper<UmsAdminRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdminRole::getRoleId, id);
        adminRoleService.remove(wrapper);
        // 删除角色菜单表的对应角色
        QueryWrapper<UmsRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsRoleMenu::getRoleId, id);
        roleMenuService.remove(queryWrapper);
        return removeById(id);
    }

    /**
     * 授权
     * @return
     */
    @Transactional
    @Override
    public boolean authMenu(AuthMenuDTO authMenuDTO) {
        // 先清空之前的授权
        QueryWrapper<UmsRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsRoleMenu::getRoleId, authMenuDTO.getId());
        roleMenuService.remove(queryWrapper);

        List<UmsRoleMenu> roleMenuList = setRoleAndMenuRelation(authMenuDTO.getMenuIds(), authMenuDTO.getId());
        return roleMenuService.saveBatch(roleMenuList);
    }

    /**
     * 根据角色id查询已分配的权限
     * @param id
     * @return
     */
    @Override
    public List<Integer> viewAuth(Integer id) {
        QueryWrapper<UmsRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsRoleMenu::getRoleId, id);
        List<Integer> menuIds = roleMenuService.list(queryWrapper.select("menu_id"))
                .stream().map(UmsRoleMenu::getMenuId).collect(Collectors.toList());
        return menuIds;
    }

    /**
     * 设置角色和菜单的关联关系
     * @param menuIds
     * @param roleId
     * @return
     */
    public List<UmsRoleMenu> setRoleAndMenuRelation(List<Integer> menuIds, Integer roleId) {
        List<UmsRoleMenu> roleMenuList = new ArrayList<>();
        for (Integer menuId : menuIds) {
            UmsRoleMenu roleMenu = new UmsRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        return roleMenuList;
    }
}
