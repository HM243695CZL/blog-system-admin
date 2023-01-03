package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hl.blog.common.api.EsPage;
import com.hl.blog.modules.blog.dto.BlogInfoGatewayDTO;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import com.hl.blog.modules.blog.dto.CommonIdDTO;
import com.hl.blog.modules.blog.esMapper.BlogEsInfoMapper;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Boolean update(BlogEsInfo blogInfo) {
        BlogEsInfo blog = esInfoMapper.findById(blogInfo.getBlogInfoId()).get();
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
        // elasticsearch的更新需要全量字段更新
        BlogEsInfo blogEsInfo = new BlogEsInfo();
        BeanUtils.copyProperties(blogInfo, blogEsInfo);
        blogEsInfo.setUpdateTime(new Date());
        blogEsInfo.setViews(blog.getViews());
        blogEsInfo.setAddTime(blog.getAddTime());
        System.out.println(blogEsInfo);
        esInfoMapper.save(blogEsInfo);

        BlogInfo info = new BlogInfo();
        BeanUtils.copyProperties(blogInfo, info);
        info.setId(blogInfo.getBlogInfoId());
        return updateById(info);
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
    public EsPage<BlogEsInfo> getBlogList(BlogInfoGatewayDTO paramsDTO) {
        PageRequest page = PageRequest.of(paramsDTO.getPageIndex() - 1, paramsDTO.getPageSize());
        List<BlogEsInfo> list = null;
        long count = 0;
        if (paramsDTO.getIsRecommend() != null) {
            // 最新推荐
            list = esInfoMapper.findAllByIsRecommendTrueOrderByAddTimeDesc(page);
        } else if (paramsDTO.getTypeId() != null) {
            // 类型
            list = esInfoMapper.findAllByTypeEqualsOrderByAddTimeDesc(page, paramsDTO.getTypeId());
            count = esInfoMapper.countAllByTypeEquals(paramsDTO.getTypeId());
        } else if (paramsDTO.getTagId() != null){
            // 标签
            list = esInfoMapper.findAllByTagsEqualsOrderByAddTimeDesc(page, paramsDTO.getTagId());
            count = esInfoMapper.countAllByTagsEquals(paramsDTO.getTagId());
        } else if (StrUtil.isNotEmpty(paramsDTO.getTitle())){
            // 标题-管理端
            list = esInfoMapper.findByTitleOrderByAddTimeDesc(page, paramsDTO.getTitle());
            count = esInfoMapper.countAllByTitle(paramsDTO.getTitle());
        } else {
            list = esInfoMapper.getAllByOrderByAddTimeDesc(page);
            count = esInfoMapper.count();
        }
        for (BlogEsInfo blogEsInfo : list) {
            blogEsInfo.setContent("");
        }
        EsPage<BlogEsInfo> pageList = new EsPage<>();
        for (BlogEsInfo blogEsInfo : list) {
            setTagNameAndTypeName(blogEsInfo);
        }
        pageList.setList(list);
        pageList.setTotal(count);
        return pageList;
    }

    /**
     * 获取博客详情
     * @param id
     * @return
     */
    @Override
    public BlogEsInfo getBlogInfo(Integer id) {
        BlogEsInfo blogInfo = esInfoMapper.findById(id).get();
        List<BlogEsInfo> list = esInfoMapper.getAllByOrderByAddTimeDesc();
        int size = list.size();
        // 获取上一个和下一个博客的id
        for (int index = 0; index <= size - 1; index++) {
            if (blogInfo.getBlogInfoId().equals(list.get(index).getBlogInfoId())) {
                if (index == 0) {
                    blogInfo.setPrevId(null);
                    blogInfo.setNextId(list.get(1).getBlogInfoId());
                } else if (index == size - 1) {
                    blogInfo.setNextId(null);
                    blogInfo.setPrevId(list.get(size - 1).getBlogInfoId());
                } else {
                    blogInfo.setPrevId(list.get(index - 1).getBlogInfoId());
                    blogInfo.setNextId(list.get(index + 1).getBlogInfoId());
                }
            }
        }
        return setTagNameAndTypeName(blogInfo);
    }

    /**
     * 更新博客浏览次数
     * @param idDTO
     * @return
     */
    @Override
    public Boolean updateBlogViews(CommonIdDTO idDTO) {
        BlogInfo blogInfo = getById(idDTO.getId());
        BlogEsInfo blogEsInfo = esInfoMapper.findById(idDTO.getId()).get();
        if(blogInfo.getViews() == null) {
            blogInfo.setViews(1);
            blogEsInfo.setViews(1);
        } else {
            blogInfo.setViews(blogInfo.getViews() + 1);
            blogEsInfo.setViews(blogEsInfo.getViews() + 1);
        }
        esInfoMapper.save(blogEsInfo);
        return updateById(blogInfo);
    }

    /**
     * 数据同步
     * @return
     */
    @Override
    public Object syncData() {
        List<BlogInfo> list = list();
        List<BlogEsInfo> blogList = new ArrayList<>();
        for (BlogInfo blogInfo : list) {
            BlogEsInfo blogEsInfo = new BlogEsInfo();
            BeanUtils.copyProperties(blogInfo, blogEsInfo);
            blogEsInfo.setBlogInfoId(blogInfo.getId());
            blogList.add(blogEsInfo);
        }
        esInfoMapper.saveAll(blogList);
        return true;
    }

    /**
     * 获取es中的数据列表
     * @param gatewayDTO
     * @return
     */
    @Override
    public List<BlogEsInfo> getListByContent(BlogInfoGatewayDTO gatewayDTO) {
        List<BlogEsInfo> list = esInfoMapper.findByContent(gatewayDTO.getContent());
        for (BlogEsInfo blogEsInfo : list) {
            setTagNameAndTypeName(blogEsInfo);
        }
        return list;
    }

    /**
     * 查看博客
     * @param id
     * @return
     */
    @Override
    public Object view(Integer id) {
        return esInfoMapper.findById(id);
    }

    /**
     * 设置博客的标签名和类型名
     * @param blogInfo
     * @return
     */
    public BlogEsInfo setTagNameAndTypeName(BlogEsInfo blogInfo) {
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
