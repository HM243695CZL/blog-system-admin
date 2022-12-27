package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.modules.blog.dto.BlogTypePageDTO;
import com.hl.blog.modules.blog.model.BlogType;
import com.hl.blog.modules.blog.mapper.BlogTypeMapper;
import com.hl.blog.modules.blog.service.BlogTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-12
 */
@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType> implements BlogTypeService {

    /**
     * 分页
     * @param paramsDTO
     * @return
     */
    @Override
    public Page<BlogType> pageList(BlogTypePageDTO paramsDTO) {
        Page<BlogType> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(paramsDTO.getName())) {
            wrapper.lambda().like(BlogType::getName, paramsDTO.getName());
        }
        return page(page, wrapper);
    }

    /**
     * 分类的博客数量加1
     * @param type
     */
    @Override
    public void increase(Integer type) {
        changeTypeNumber(type, "+");
    }

    /**
     * 分类的博客数量减1
     * @param type
     */
    @Override
    public void decrease(Integer type) {
        changeTypeNumber(type, "-");
    }

    /**
     * 更新
     * @param blogType
     * @return
     */
    @Override
    public Boolean update(BlogType blogType) {
        BlogType type = getById(blogType);
        blogType.setNumber(type.getNumber());
        return updateById(blogType);
    }

    /**
     * 改变分类的数量
     * @param type
     * @param symbol
     */
    public void changeTypeNumber(Integer type, String symbol) {
        BlogType typeInfo = getById(type);
        if (typeInfo != null) {
            Integer number = symbol.equals("+") ? (typeInfo.getNumber() + 1) : (typeInfo.getNumber() - 1);
            typeInfo.setNumber(number);
            updateById(typeInfo);
        }
    }
}
