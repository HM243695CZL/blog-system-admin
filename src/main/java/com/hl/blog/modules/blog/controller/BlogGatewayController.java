package com.hl.blog.modules.blog.controller;

import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import com.hl.blog.modules.blog.dto.BlogInfoGatewayDTO;
import com.hl.blog.modules.blog.dto.CommonIdDTO;
import com.hl.blog.modules.blog.service.BlogCommentService;
import com.hl.blog.modules.blog.service.BlogInfoService;
import com.hl.blog.modules.blog.service.BlogTagService;
import com.hl.blog.modules.blog.service.BlogTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 博客门户控制器
 */
@RestController
@RequestMapping("/gateway")
@Api(value = "门户", description = "门户信息")
public class BlogGatewayController {

    @Autowired
    private BlogInfoService infoService;

    @Autowired
    private BlogTypeService typeService;

    @Autowired
    private BlogTagService tagService;

    @Autowired
    private BlogCommentService commentService;

    // 获取博客列表
    @LogAnnotation
    @ApiOperation("获取博客列表")
    @RequestMapping(value = "/getBlogList", method = RequestMethod.POST)
    public CommonResult getBlogList(@RequestBody BlogInfoGatewayDTO paramsDTO) {
        return CommonResult.success(infoService.getBlogList(paramsDTO));
    }

    // 获取分类列表
    @LogAnnotation
    @ApiOperation("获取分类列表")
    @RequestMapping(value = "/getTypeList", method = RequestMethod.GET)
    public CommonResult getTypeList(){
        return CommonResult.success(typeService.list());
    }

    // 获取标签列表
    @LogAnnotation
    @ApiOperation("获取标签列表")
    @RequestMapping(value = "/getTagList", method = RequestMethod.GET)
    public CommonResult getTagList() {
        return CommonResult.success(tagService.list());
    }

    // 获取博客详情
    @LogAnnotation
    @ApiOperation("获取博客详情")
    @RequestMapping(value = "/getBlogInfo/{id}", method = RequestMethod.GET)
    public CommonResult getBlogInfo(@PathVariable Integer id) {
        return CommonResult.success(infoService.getBlogInfo(id));
    }

    // 根据博客id获取评论
    @LogAnnotation
    @ApiOperation("根据博客id获取评论")
    @RequestMapping(value = "/getCommentByBlogId/{id}", method = RequestMethod.GET)
    public CommonResult getCommentByBlogId(@PathVariable String id) {
        return CommonResult.success(commentService.getCommentByBlogId(id));
    }

    // 更新浏览次数
    @LogAnnotation
    @ApiOperation("更新博客浏览次数")
    @RequestMapping(value = "/updateBlogViews", method = RequestMethod.POST)
    public CommonResult updateBlogViews(@RequestBody CommonIdDTO idDTO) {
        return CommonResult.success(infoService.updateBlogViews(idDTO));
    }

    // 获取es中的数据列表
    @LogAnnotation
    @ApiModelProperty("获取es中的数据列表")
    @RequestMapping(value = "/getListByContent", method = RequestMethod.POST)
    public CommonResult getListByContent(@RequestBody BlogInfoGatewayDTO gatewayDTO) {
        return CommonResult.success(infoService.getListByContent(gatewayDTO));
    }

}
