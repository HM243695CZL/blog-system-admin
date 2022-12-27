package com.hl.blog.modules.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.modules.blog.dto.TagPageDTO;
import com.hl.blog.modules.blog.model.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客标签表 服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
public interface BlogTagService extends IService<BlogTag> {

    Page<BlogTag> pageList(TagPageDTO pageDto);

    /**
     * 标签的博客数量增加
     * @param tags
     */
    void increase(Integer tags);

    /**
     * 标签的博客数量减少
     * @param tags
     */
    void decrease(Integer tags);

    Boolean update(BlogTag blogTag);
}
