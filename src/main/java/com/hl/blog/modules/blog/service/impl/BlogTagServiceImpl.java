package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.modules.blog.dto.TagPageDTO;
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
    public Page<BlogTag> pageList(TagPageDTO pageDto) {
        Page<BlogTag> page = new Page<>(pageDto.getPageIndex(), pageDto.getPageSize());
        QueryWrapper<BlogTag> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(pageDto.getName())) {
            queryWrapper.lambda().like(BlogTag::getName, pageDto.getName());
        }
        return page(page, queryWrapper);
    }

    /**
     * 标签的博客数量增加
     * @param tags
     */
    @Override
    public void increase(Integer tags) {
        changeTagNumber(tags, "+");
    }

    /**
     * 标签的博客数量减少
     * @param tags
     */
    @Override
    public void decrease(Integer tags) {
        changeTagNumber(tags, "-");
    }

    /**
     * 改变标签的数量
     * @param tags
     * @param symbol
     */
    public void changeTagNumber(Integer tags, String symbol) {
        BlogTag tag = getById(tags);
        if (tag != null) {
            Integer number = symbol.equals("+") ? (tag.getNumber() + 1) : (tag.getNumber() - 1);
            tag.setNumber(number);
            updateById(tag);
        }
    }
}
