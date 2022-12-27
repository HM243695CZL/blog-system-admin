package com.hl.blog.modules.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.modules.blog.dto.BlogTypePageDTO;
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

    Page<BlogType> pageList(BlogTypePageDTO paramsDTO);

    /**
     * 分类的博客数量加1
     * @param type
     */
    void increase(Integer type);

    /**
     * 分类的博客数量减1
     * @param type
     */
    void decrease(Integer type);

    Boolean update(BlogType blogType);
}
