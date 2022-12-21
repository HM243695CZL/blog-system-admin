package com.hl.blog.modules.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.modules.blog.model.BlogComment;
import com.hl.blog.modules.blog.mapper.BlogCommentMapper;
import com.hl.blog.modules.blog.service.BlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-21
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {


    /**
     * 根据博客id获取评论
     * @param id
     * @return
     */
    @Override
    public List<BlogComment> getCommentByBlogId(String id) {
        QueryWrapper<BlogComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogComment::getBlogId, id);
        return list(queryWrapper);
    }
}
