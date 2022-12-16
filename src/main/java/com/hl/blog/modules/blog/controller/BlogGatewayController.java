package com.hl.blog.modules.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.api.CommonPage;
import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import com.hl.blog.common.vo.PageParamsDTO;
import com.hl.blog.modules.blog.model.BlogInfo;
import com.hl.blog.modules.blog.service.BlogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客门户控制器
 */
@RestController
@RequestMapping("/gateway")
@Api(value = "门户", description = "门户信息")
public class BlogGatewayController {

    @Autowired
    private BlogInfoService blogInfoService;

    // 获取博客列表
    @LogAnnotation
    @ApiOperation("获取博客列表")
    @RequestMapping(value = "/getBlogList", method = RequestMethod.POST)
    public CommonResult getBlogList(@RequestBody PageParamsDTO paramsDTO) {
        Page<BlogInfo> blogList = blogInfoService.getBlogList(paramsDTO);
        return CommonResult.success(CommonPage.restPage(blogList));
    }
}
