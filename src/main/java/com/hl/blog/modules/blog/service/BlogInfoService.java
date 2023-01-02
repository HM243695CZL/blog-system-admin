package com.hl.blog.modules.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.api.EsPage;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.dto.BlogInfoGatewayDTO;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import com.hl.blog.modules.blog.dto.CommonIdDTO;
import com.hl.blog.modules.blog.model.BlogEsInfo;
import com.hl.blog.modules.blog.model.BlogInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    Boolean delete(Integer id);

    /**
     * 获取博客列表
     * @param paramsDTO
     * @return
     */
    EsPage<BlogEsInfo> getBlogList(BlogInfoGatewayDTO paramsDTO);

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    BlogEsInfo getBlogInfo(Integer id);

    /**
     * 更新博客浏览次数
     * @param idDTO
     * @return
     */
    Boolean updateBlogViews(CommonIdDTO idDTO);

    /**
     * 数据同步
     * @return
     */
    Object syncData();

    /**
     * 获取es中的数据列表
     * @param gatewayDTO
     * @return
     */
    List<BlogEsInfo> getListByContent(BlogInfoGatewayDTO gatewayDTO);
}
