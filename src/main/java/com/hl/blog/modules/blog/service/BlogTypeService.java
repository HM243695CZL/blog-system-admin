package com.hl.blog.modules.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.dto.BlogTypePageDto;
import com.hl.blog.modules.blog.model.BlogType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-12
 */
public interface BlogTypeService extends IService<BlogType> {

    Page<BlogType> getList(BlogTypePageDto paramsDTO);
}
