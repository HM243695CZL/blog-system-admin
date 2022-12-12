package com.hl.blog.modules.ums.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.blog.common.constants.Constants;
import com.hl.blog.common.exception.ApiException;
import com.hl.blog.common.exception.Asserts;
import com.hl.blog.common.util.IpUtil;
import com.hl.blog.common.util.JwtTokenUtil;
import com.hl.blog.domain.AdminUserDetails;
import com.hl.blog.modules.ums.dto.AdminPageDTO;
import com.hl.blog.modules.ums.dto.UpdatePassDTO;
import com.hl.blog.modules.ums.mapper.UmsAdminMapper;
import com.hl.blog.modules.ums.model.UmsAdmin;
import com.hl.blog.modules.ums.model.UmsAdminRole;
import com.hl.blog.modules.ums.model.UmsRole;
import com.hl.blog.modules.ums.service.UmsAdminRoleService;
import com.hl.blog.modules.ums.service.UmsAdminService;
import com.hl.blog.modules.ums.service.UmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-06-20
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UmsAdminRoleService adminRoleService;

    @Autowired
    UmsRoleService roleService;

    /**
     * 分页查询
     * @param paramsDTO
     * @return
     */
    @Override
    public Page<UmsAdmin> pageList(AdminPageDTO paramsDTO) {
        Page<UmsAdmin> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(paramsDTO.getUsername())) {
            queryWrapper.lambda().like(UmsAdmin::getUsername, paramsDTO.getUsername());
        }
        // 根据分页查询用户
        Page<UmsAdmin> pageList = page(page, queryWrapper);
        // 根据用户id获取角色id
        page.getRecords().stream().forEach(this::setUserRoles);
        return pageList;
    }

    /**
     * 添加用户
     * @param umsAdmin
     * @return
     */
    @Transactional
    @Override
    public boolean create(UmsAdmin umsAdmin) {
        // 设置用户初始密码
        umsAdmin.setPassword(Constants.INIT_PASSWORD);
        boolean result = save(umsAdmin);
        List<UmsAdminRole> adminRoleList = setAdminAndRole(umsAdmin.getRoleIds(), umsAdmin.getId());
        adminRoleService.saveBatch(adminRoleList);
        return result;
    }

    /**
     * 更新用户
     * @param umsAdmin
     * @return
     */
    @Transactional
    @Override
    public boolean updateAdmin(UmsAdmin umsAdmin) {
        UmsAdmin admin = getById(umsAdmin.getId());
        umsAdmin.setPassword(admin.getPassword());
        boolean result = updateById(umsAdmin);
        // 清空当前用户的所有角色
        QueryWrapper<UmsAdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdminRole::getAdminId, umsAdmin.getId());
        adminRoleService.remove(queryWrapper);

        List<UmsAdminRole> adminRoleList = setAdminAndRole(umsAdmin.getRoleIds(), umsAdmin.getId());
        adminRoleService.saveBatch(adminRoleList);

        return result;
    }

    /**
     * 查看用户
     * @param id
     * @return
     */
    @Override
    public UmsAdmin view(String id) {
        UmsAdmin admin = getById(id);
        // 查询用户所有的角色id
        List<Integer> roleIds = adminRoleService.list(new QueryWrapper<UmsAdminRole>().eq("admin_id", id))
                .stream().map(UmsAdminRole::getRoleId).collect(Collectors.toList());
        admin.setRoleIds(roleIds);
        return admin;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean delete(String id) {
        // 清空当前用户的所有角色
        QueryWrapper<UmsAdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdminRole::getAdminId, id);
        adminRoleService.remove(queryWrapper);
        return removeById(id);
    }

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    @Override
    public AdminUserDetails loadUserByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            return new AdminUserDetails(admin);
        }
        throw new ApiException("用户不存在");
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public String login(String username, String password, HttpServletRequest request) {
        String token = null;
        try {
            AdminUserDetails userDetails = loadUserByUsername(username);
            if (userDetails == null) {
                Asserts.fail("用户名不存在");
            }
            if (!userDetails.getPassword().equals(password)) {
                Asserts.fail("密码错误");
            }
            // 生成springSecurity的通过认证标识
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            // 更新上次登录时间和登录ip
            UmsAdmin currentAdmin = getCurrentAdmin();
            UpdateWrapper<UmsAdmin> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().eq(UmsAdmin::getId, currentAdmin.getId());
            updateWrapper.set("last_login_ip", IpUtil.getIpAddr(request));
            updateWrapper.set("last_login_time", new Date());
            update(updateWrapper);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常：{}", e.getMessage());
        }
        return token;
    }

    /**
     * 修改密码
     * @return
     */
    @Override
    public boolean updatePass(UpdatePassDTO passDTO) {
        UpdateWrapper<UmsAdmin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(UmsAdmin::getId, passDTO.getId());
        updateWrapper.set("password", passDTO.getPassword());
        return update(updateWrapper);
    }

    /**
     * 设置用户和角色的关联关系
     * @param roleList 角色列表
     * @param userId 用户id
     * @return
     */
    public List<UmsAdminRole> setAdminAndRole(List<Integer> roleList, Integer userId) {
        List<UmsAdminRole> adminRoleList = new ArrayList<>();
        for (Integer roleId : roleList) {
            UmsAdminRole adminRole = new UmsAdminRole();
            adminRole.setAdminId(userId);
            adminRole.setRoleId(roleId);
            adminRoleList.add(adminRole);
        }
        return adminRoleList;
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return
     */
    public UmsAdmin getAdminByUsername(String username) {
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername, username);
        return getOne(wrapper);
    }

    /**
     * 获取当前用户
     * @return
     */
    public UmsAdmin getCurrentAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails admin = (AdminUserDetails) authentication.getPrincipal();
        UmsAdmin userInfo = admin.getUmsAdmin();
        setUserRoles(userInfo);
        return admin.getUmsAdmin();
    }

    /**
     * 设置用户的roles字段
     * @param userInfo
     */
    private void setUserRoles(UmsAdmin userInfo) {
        List<Integer> roleIds = adminRoleService.list(new QueryWrapper<UmsAdminRole>().eq("admin_id", userInfo.getId())
                .select("role_id")).stream().map(UmsAdminRole::getRoleId).collect(Collectors.toList());
        if(!ObjectUtil.isEmpty(roleIds)) {
            // 根据角色表查询对应的角色名称
            List<String> roleName = roleService.listByIds(roleIds).stream().map(UmsRole::getKey).collect(Collectors.toList());
            userInfo.setRoles(roleName);
        }
    }
}
