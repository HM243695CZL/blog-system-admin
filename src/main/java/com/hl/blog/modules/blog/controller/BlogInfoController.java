package com.hl.blog.modules.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.api.CommonPage;
import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import com.hl.blog.modules.blog.dto.BlogInfoPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.hl.blog.modules.blog.service.BlogInfoService;
import com.hl.blog.modules.blog.model.BlogInfo;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 博客详情表 前端控制器
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/admin/blog-info")
@Api(tags = "博客详情", description = "博客详情")
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;

    // 分页
    @LogAnnotation()
    @ApiOperation("分页查询")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public CommonResult page(@RequestBody BlogInfoPageDTO paramsDTO) {
        Page<BlogInfo> pageList = blogInfoService.pageList(paramsDTO);
        return CommonResult.success(CommonPage.restPage(pageList));
    }

    // 新增
    @LogAnnotation()
    @ApiOperation("新增博客")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult save(@Valid @RequestBody BlogInfo blogInfo) {
        return CommonResult.success(blogInfoService.create(blogInfo));
    }

    // 更新
    @LogAnnotation()
    @ApiOperation("更新博客")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody BlogInfo blogInfo) {
        return CommonResult.success(blogInfoService.update(blogInfo));
    }

    // 删除
    @LogAnnotation()
    @ApiOperation("删除博客")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Integer id) {
        return CommonResult.success( blogInfoService.delete(id));
    }

    // 获取全部
    @LogAnnotation()
    @ApiOperation("获取全部博客")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list() {
        return CommonResult.success(blogInfoService.list());
    }

    // 查看
    @LogAnnotation()
    @ApiOperation("查看博客")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public CommonResult findOne(@PathVariable String id) {
        return CommonResult.success(blogInfoService.getById(id));
    }

    // 数据同步
    @LogAnnotation
    @ApiOperation("数据同步")
    @RequestMapping(value = "/syncData", method = RequestMethod.GET)
    public CommonResult syncData() {
        return CommonResult.success(blogInfoService.syncData());
    }

}

