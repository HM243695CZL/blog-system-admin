package com.hl.blog.modules.blog.controller;

import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.hl.blog.modules.blog.service.BlogCommentService;
import com.hl.blog.modules.blog.model.BlogComment;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客评论表 前端控制器
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-21
 */
@RestController
@RequestMapping("/gateway/blog-comment")
@Api(tags = "博客评论", description = "博客评论")
public class BlogCommentController {

    @Autowired
    private BlogCommentService blogCommentService;

    // 新增
    @LogAnnotation
    @ApiOperation("新增评论")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult save(@RequestBody BlogComment blogComment) {
        return CommonResult.success(blogCommentService.save(blogComment));
    }

    // 更新
    @LogAnnotation
    @ApiOperation("更新评论")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody BlogComment blogComment) {
        return CommonResult.success(blogCommentService.updateById(blogComment));
    }

    // 删除
    @LogAnnotation
    @ApiOperation("删除评论")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable String id) {
        return CommonResult.success( blogCommentService.removeById(id));
    }

    // 获取全部
    @LogAnnotation
    @ApiOperation("获取全部评论")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list() {
        return CommonResult.success(blogCommentService.list());
    }

    // 查看
    @LogAnnotation
    @ApiOperation("查看评论")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public CommonResult findOne(@PathVariable String id) {
        return CommonResult.success(blogCommentService.getById(id));
    }

}

