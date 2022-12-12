package com.hl.blog.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.blog.domain.AdminUserDetails;
import com.hl.blog.modules.ums.dto.AdminPageDTO;
import com.hl.blog.modules.ums.dto.UpdatePassDTO;
import com.hl.blog.modules.ums.model.UmsAdmin;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-06-20
 */
public interface UmsAdminService extends IService<UmsAdmin> {

    Page<UmsAdmin> pageList(AdminPageDTO paramsDTO);

    boolean create(UmsAdmin umsAdmin);

    boolean updateAdmin(UmsAdmin umsAdmin);

    UmsAdmin view(String id);

    boolean delete(String id);

    AdminUserDetails loadUserByUsername(String username);

    // 获取当前登录用户
    UmsAdmin getCurrentAdmin();

    String login(String username, String password, HttpServletRequest request);

    boolean updatePass(UpdatePassDTO passDTO);
}
