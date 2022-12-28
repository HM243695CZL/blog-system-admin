package com.hl.blog.modules.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.dto.BlogInfoGatewayDTO;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import com.hl.blog.modules.blog.dto.CommonIdDTO;
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
    Page<BlogInfo> getBlogList(BlogInfoGatewayDTO paramsDTO);

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    BlogInfo getBlogInfo(String id);

    /**
     * 更新博客浏览次数
     * @param idDTO
     * @return
     */
    Boolean updateBlogViews(CommonIdDTO idDTO);
}
