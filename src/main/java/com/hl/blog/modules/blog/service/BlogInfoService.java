package com.hl.blog.modules.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import com.hl.blog.modules.blog.model.BlogInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客详情表 服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
public interface BlogInfoService extends IService<BlogInfo> {

    Page<BlogInfo> pageList(BlogInfoPageDTO paramsDTO);

    Boolean create(BlogInfo blogInfo);

    Boolean update(BlogInfo blogInfo);

    Boolean delete(String id);

    /**
     * 获取博客列表
     * @param paramsDTO
     * @return
     */
    Page<BlogInfo> getBlogList(PageParamsDTO paramsDTO);
}
