package com.hl.blog.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hl.blog.modules.ums.dto.FormKeyDTO;
import com.hl.blog.modules.ums.dto.FormPageDTO;
import com.hl.blog.modules.ums.model.UmsForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-11-16
 */
public interface UmsFormService extends IService<UmsForm> {

    Page<UmsForm> pageList(FormPageDTO paramsDTO);

    /**
     * 根据key获取表单配置
     * @param formKeyDTO
     * @return
     */
    UmsForm getConfigByKey(FormKeyDTO formKeyDTO);
}
