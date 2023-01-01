package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.blog.modules.blog.dto.BlogInfoGatewayDTO;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import com.hl.blog.modules.blog.dto.CommonIdDTO;
import com.hl.blog.modules.blog.mapper.BlogEsInfoMapper;
import com.hl.blog.modules.blog.mapper.BlogInfoMapper;
import com.hl.blog.modules.blog.model.BlogEsInfo;
import com.hl.blog.modules.blog.model.BlogInfo;
import com.hl.blog.modules.blog.model.BlogTag;
import com.hl.blog.modules.blog.model.BlogType;
import com.hl.blog.modules.blog.service.BlogInfoService;
import com.hl.blog.modules.blog.service.BlogTagService;
import com.hl.blog.modules.blog.service.BlogTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    BlogInfoMapper blogInfoMapper;

    @Autowired
    BlogEsInfoMapper esInfoMapper;

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
        queryWrapper.orderByDesc("add_time");
        Page<BlogInfo> pageList = page(page, queryWrapper);
        for (BlogInfo item : pageList.getRecords()) {
            item.setContent(""); // 将content内容置空
            if (item.getTags() != null) {
                BlogTag tagInfo = tagService.getById(item.getTags());
                if (tagInfo != null) {
                    item.setTagsName(tagInfo.getName());
                }
            }
            if (item.getType() != null) {
                BlogType typeInfo = typeService.getById(item.getType());
                if (typeInfo != null) {
                    item.setTypeName(typeInfo.getName());
                }
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
//        save(blogInfo);
        blogInfoMapper.insert(blogInfo);
        if (blogInfo.getTags() != null) {
            // 更新标签的博客数量
            tagService.increase(blogInfo.getTags());
        }
        if (blogInfo.getType() != null) {
            // 更新分类的博客数量
            typeService.increase(blogInfo.getType());
        }
        BlogEsInfo blogEsInfo = new BlogEsInfo();
        BeanUtils.copyProperties(blogInfo, blogEsInfo);
        blogEsInfo.setBlogInfoId(blogInfo.getId());
        esInfoMapper.save(blogEsInfo);
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
            if (blog.getTags() != null) {
                if (blog.getTags().equals(blogInfo.getTags())) {
                    // 当前的标签减1
                    tagService.decrease(blogInfo.getTags());
                } else {
                    // 之前的标签减1
                    tagService.decrease(blog.getTags());
                }
            }
        }
        // 如果有类型，当前类型加1
        if (blogInfo.getType() != null) {
            typeService.increase(blogInfo.getType());
            if (blog.getType() != null) {
                if (blog.getType().equals(blogInfo.getType())) {
                    // 当前的类型减1
                    typeService.decrease(blogInfo.getType());
                } else {
                    // 之前的类型减1
                    typeService.decrease(blog.getType());
                }
            }
        }
        blogInfo.setViews(blog.getViews());
        blogInfo.setAddTime(blog.getAddTime());
        BlogEsInfo blogEsInfo = new BlogEsInfo();
        BeanUtils.copyProperties(blogInfo, blogEsInfo);
        esInfoMapper.save(blogEsInfo);
        return updateById(blogInfo);
    }

    /**
     * 删除博客
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Boolean delete(Integer id) {
        BlogInfo blogInfo = getById(id);
        if (blogInfo.getTags() != null) {
            tagService.decrease(blogInfo.getTags());
        }
        if (blogInfo.getType() != null) {
            typeService.decrease(blogInfo.getType());
        }
        removeById(id);
        esInfoMapper.deleteById(id);
        return true;
    }

    /**
     * 获取博客列表
     * @param paramsDTO
     * @return
     */
    @Override
    public Page<BlogInfo> getBlogList(BlogInfoGatewayDTO paramsDTO) {
        Page<BlogInfo> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
        if (paramsDTO.getState() != null) {
            queryWrapper.lambda().eq(BlogInfo::getState, paramsDTO.getState());
        }
        if (paramsDTO.getIsRecommend() != null) {
            queryWrapper.lambda().eq(BlogInfo::getIsRecommend, paramsDTO.getIsRecommend());
        }
        if (paramsDTO.getTypeId() != null) {
            queryWrapper.lambda().eq(BlogInfo::getType, paramsDTO.getTypeId());
        }
        if (paramsDTO.getTagId() != null) {
            queryWrapper.lambda().eq(BlogInfo::getTags, paramsDTO.getTagId());
        }
        queryWrapper.orderByDesc("add_time");
        Page<BlogInfo> pageList = page(page, queryWrapper);
        for (BlogInfo item : pageList.getRecords()) {
            setTagNameAndTypeName(item);
        }
        return pageList;
    }

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    @Override
    public BlogEsInfo getBlogInfo(Integer id) {
        return esInfoMapper.findById(id).get();
//        BlogInfo blogInfo = getById(id);
//        QueryWrapper<BlogInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("add_time");
//        List<BlogInfo> list = list(queryWrapper);
//        int size = list.size();
//        // 获取上一个和下一个博客的id
//        for (int index = 0; index <= size - 1; index++) {
//            if (blogInfo.getId().equals(list.get(index).getId())) {
//                if (index == 0) {
//                    blogInfo.setPrevId(null);
//                    blogInfo.setNextId(list.get(1).getId());
//                } else if (index == size - 1) {
//                    blogInfo.setNextId(null);
//                    blogInfo.setPrevId(list.get(size - 1).getId());
//                } else {
//                    blogInfo.setPrevId(list.get(index - 1).getId());
//                    blogInfo.setNextId(list.get(index + 1).getId());
//                }
//            }
//        }
//        return setTagNameAndTypeName(blogInfo);
    }

    /**
     * 更新博客浏览次数
     * @param idDTO
     * @return
     */
    @Override
    public Boolean updateBlogViews(CommonIdDTO idDTO) {
        BlogInfo blogInfo = getById(idDTO.getId());
        if(blogInfo.getViews() == null) {
            blogInfo.setViews(1);
        } else {
            blogInfo.setViews(blogInfo.getViews() + 1);
        }
        return updateById(blogInfo);
    }

    public BlogInfo setTagNameAndTypeName(BlogInfo blogInfo) {
        if (blogInfo.getType() != null) {
            BlogType typeInfo = typeService.getById(blogInfo.getType());
            blogInfo.setTypeName(typeInfo.getName());
        }
        if (blogInfo.getTags() != null) {
            BlogTag tagInfo = tagService.getById(blogInfo.getTags());
            blogInfo.setTagsName(tagInfo.getName());
        }
        return blogInfo;
    }
}
