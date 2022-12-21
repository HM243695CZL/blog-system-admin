package com.hl.blog.modules.blog.service;

import com.hl.blog.modules.blog.model.BlogComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客评论表 服务类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-21
 */
public interface BlogCommentService extends IService<BlogComment> {

    /**
     * 根据博客id获取评论
     * @param id
     * @return
     */
    List<BlogComment> getCommentByBlogId(String id);
}
