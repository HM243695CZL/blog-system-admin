package com.hl.blog.modules.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.api.CommonPage;
import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import com.hl.blog.modules.blog.dto.TagPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.hl.blog.modules.blog.service.BlogTagService;
import com.hl.blog.modules.blog.model.BlogTag;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 博客标签表 前端控制器
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/admin/blog-tag")
@Api(tags = "标签管理", description = "标签管理")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    // 分页
    @LogAnnotation()
    @ApiOperation("分页查询")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public CommonResult page(@RequestBody TagPageDTO pageDto) {
        Page<BlogTag> pageList =  blogTagService.pageList(pageDto);
        return CommonResult.success(CommonPage.restPage(pageList));
    }

    // 新增
    @LogAnnotation()
    @ApiOperation("新增标签")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult save(@Valid @RequestBody BlogTag blogTag) {
        return CommonResult.success(blogTagService.save(blogTag));
    }

    // 更新
    @LogAnnotation()
    @ApiOperation("更新标签")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody BlogTag blogTag) {
        return CommonResult.success(blogTagService.updateById(blogTag));
    }

    // 删除
    @LogAnnotation()
    @ApiOperation("删除标签")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable String id) {
        return CommonResult.success( blogTagService.removeById(id));
    }

    // 获取全部
    @LogAnnotation()
    @ApiOperation("获取全部标签")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list() {
        return CommonResult.success(blogTagService.list());
    }

    // 查看
    @LogAnnotation()
    @ApiOperation("查看标签")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public CommonResult findOne(@PathVariable String id) {
        return CommonResult.success(blogTagService.getById(id));
    }

}

