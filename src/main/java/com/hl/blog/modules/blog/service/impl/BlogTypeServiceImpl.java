package com.hl.blog.modules.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.dto.BlogTypePageDto;
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
    public Page<BlogType> pageList(BlogTypePageDto paramsDTO) {
        Page<BlogType> page = new Page<>(paramsDTO.getPageIndex(), paramsDTO.getPageSize());
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(paramsDTO.getName())) {
            wrapper.lambda().like(BlogType::getName, paramsDTO.getName());
        }
        return page(page, wrapper);
    }
}
