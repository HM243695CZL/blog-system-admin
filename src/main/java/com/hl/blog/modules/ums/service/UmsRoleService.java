package com.hl.blog.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.ums.dto.AuthMenuDTO;
import com.hl.blog.modules.ums.model.UmsRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-06-19
 */
public interface UmsRoleService extends IService<UmsRole> {

    Page<UmsRole> pageList(PageParamsDTO paramsDTO);

    boolean delete(String id);

    /**
     * 授权
     * @return
     */
    boolean authMenu(AuthMenuDTO authMenuDTO);

    /**
     * 根据角色id查询已分配的权限
     * @param id
     * @return
     */
    List<Integer> viewAuth(Integer id);
}
