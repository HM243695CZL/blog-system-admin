package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.modules.blog.dto.TagPageDto;
import com.hl.blog.modules.blog.model.BlogTag;
import com.hl.blog.modules.blog.mapper.BlogTagMapper;
import com.hl.blog.modules.blog.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Override
    public Page<BlogTag> pageList(TagPageDto pageDto) {
        Page<BlogTag> page = new Page<>(pageDto.getPageIndex(), pageDto.getPageSize());
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(pageDto.getName())) {
            queryWrapper.lambda().like(BlogTag::getName, pageDto.getName());
        }
        return page(page, queryWrapper);
    }
}
