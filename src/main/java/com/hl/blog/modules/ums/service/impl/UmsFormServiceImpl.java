package com.hl.blog.modules.ums.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.exception.ApiException;
import com.hl.blog.modules.ums.dto.FormKeyDTO;
import com.hl.blog.modules.ums.dto.FormPageDTO;
import com.hl.blog.modules.ums.model.UmsForm;
import com.hl.blog.modules.ums.mapper.UmsFormMapper;
import com.hl.blog.modules.ums.service.UmsFormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-11-16
 */
@Service
public class UmsFormServiceImpl extends ServiceImpl<UmsFormMapper, UmsForm> implements UmsFormService {

    /**
     * 分页查询
     * @param paramsDTO
     * @return
     */
    @Override
    public Page<UmsForm> pageList(FormPageDTO paramsDTO) {
        Page<UmsForm> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<UmsForm> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(paramsDTO.getName())) {
            queryWrapper.lambda().like(UmsForm::getName, paramsDTO.getName());
        }
        if (StrUtil.isNotEmpty(paramsDTO.getFormKey())) {
            queryWrapper.lambda().like(UmsForm::getFormKey, paramsDTO.getFormKey());
        }
        if (StrUtil.isNotEmpty(paramsDTO.getRemark())) {
            queryWrapper.lambda().like(UmsForm::getRemark, paramsDTO.getRemark());
        }
        return page(page, queryWrapper);
    }

    /**
     * 根据key获取表单配置
     * @param formKeyDTO
     * @return
     */
    @Override
    public UmsForm getConfigByKey(FormKeyDTO formKeyDTO) {
        QueryWrapper<UmsForm> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsForm::getFormKey, formKeyDTO.getKey());
        UmsForm config = getOne(wrapper);
        if (config == null) {
            throw new ApiException("表单配置不存在");
        }
        return getOne(wrapper);
    }
}
