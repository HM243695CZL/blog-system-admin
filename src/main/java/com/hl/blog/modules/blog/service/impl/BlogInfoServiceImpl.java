package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import com.hl.blog.modules.blog.model.BlogInfo;
import com.hl.blog.modules.blog.mapper.BlogInfoMapper;
import com.hl.blog.modules.blog.model.BlogTag;
import com.hl.blog.modules.blog.model.BlogType;
import com.hl.blog.modules.blog.service.BlogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.blog.modules.blog.service.BlogTagService;
import com.hl.blog.modules.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 博客详情表 服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
@Service
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements BlogInfoService {


    @Autowired
    BlogTagService tagService;

    @Autowired
    BlogTypeService typeService;

    @Override
    public Page<BlogInfo> pageList(BlogInfoPageDTO paramsDTO) {
        Page<BlogInfo> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(paramsDTO.getTitle())) {
            queryWrapper.lambda().like(BlogInfo::getTitle, paramsDTO.getTitle());
        }
        if (StrUtil.isNotEmpty(paramsDTO.getType())) {
            queryWrapper.lambda().eq(BlogInfo::getType, paramsDTO.getType());
        }
        if (StrUtil.isNotEmpty(paramsDTO.getTags())) {
            queryWrapper.lambda().eq(BlogInfo::getTags, paramsDTO.getTags());
        }
        Page<BlogInfo> pageList = page(page, queryWrapper);
        for (BlogInfo item : pageList.getRecords()) {
            item.setContent(""); // 将content内容置空
            if (item.getTags() != null) {
                BlogTag tagInfo = tagService.getById(item.getTags());
                item.setTagsName(tagInfo.getName());
            }
            if (item.getType() != null) {
                BlogType typeInfo = typeService.getById(item.getType());
                item.setTypeName(typeInfo.getName());
            }
        }
        return pageList;
    }

    /**
     * 添加博客
     * @param blogInfo
     * @return
     */
    @Transactional
    @Override
    public Boolean create(BlogInfo blogInfo) {
        save(blogInfo);
        if (blogInfo.getTags() != null) {
            // 更新标签的博客数量
            tagService.increase(blogInfo.getTags());
        }
        if (blogInfo.getType() != null) {
            // 更新分类的博客数量
            typeService.increase(blogInfo.getType());
        }
        return true;
    }

    /**
     * 更新博客
     * @param blogInfo
     * @return
     */
    @Transactional
    @Override
    public Boolean update(BlogInfo blogInfo) {
        BlogInfo blog = getById(blogInfo);
        // 如果有标签，当前标签加1
        if (blogInfo.getTags() != null) {
            tagService.increase(blogInfo.getTags());
            if (blog.getTags().equals(blogInfo.getTags())) {
                // 当前的标签减1
                tagService.decrease(blogInfo.getTags());
            } else {
                // 之前的标签减1
                tagService.decrease(blog.getTags());
            }
        }
        // 如果有类型，当前类型加1
        if (blogInfo.getType() != null) {
            typeService.increase(blogInfo.getType());
            if (blog.getType().equals(blogInfo.getType())) {
                // 当前的类型减1
                typeService.decrease(blogInfo.getType());
            } else {
                // 之前的类型减1
                typeService.decrease(blog.getType());
            }
        }
        updateById(blogInfo);
        return true;
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Boolean delete(String id) {
        BlogInfo blogInfo = getById(id);
        if (blogInfo.getTags() != null) {
            tagService.decrease(blogInfo.getTags());
        }
        if (blogInfo.getType() != null) {
            typeService.decrease(blogInfo.getType());
        }
        removeById(id);
        return true;
    }

    /**
     * 获取博客列表
     * @param paramsDTO
     * @return
     */
    @Override
    public Page<BlogInfo> getBlogList(PageParamsDTO paramsDTO) {
        Page<BlogInfo> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getState, true);
        Page<BlogInfo> pageList = page(page, queryWrapper);
        for (BlogInfo item : pageList.getRecords()) {
            if (item.getType() != null) {
                BlogType typeInfo = typeService.getById(item.getType());
                item.setTypeName(typeInfo.getName());
            }
        };
        return pageList;
    }
}
