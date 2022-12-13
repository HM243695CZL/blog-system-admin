package com.hl.blog.modules.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.api.CommonPage;
import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import com.hl.blog.modules.blog.dto.BlogTypePageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.hl.blog.modules.blog.service.BlogTypeService;
import com.hl.blog.modules.blog.model.BlogType;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-12-12
 */
@RestController
@RequestMapping("/admin/blog-type")
@Api(tags = "分类管理", description = "分类管理")
public class BlogTypeController {


    @Autowired
    private BlogTypeService blogTypeService;

    // 分页
    @LogAnnotation()
    @ApiOperation("分页查询")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public CommonResult page(@RequestBody BlogTypePageDto paramsDTO) {
        Page<BlogType> typeList = blogTypeService.getList(paramsDTO);
        return CommonResult.success(CommonPage.restPage(typeList));
    }

    // 新增
    @LogAnnotation()
    @ApiOperation("新增分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult save(@RequestBody BlogType blogType) {
        return CommonResult.success(blogTypeService.save(blogType));
    }

    // 更新
    @LogAnnotation()
    @ApiOperation("更新分类")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody BlogType blogType) {
        return CommonResult.success(blogTypeService.updateById(blogType));
    }

    // 删除
    @LogAnnotation()
    @ApiOperation("删除分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable String id) {
        return CommonResult.success( blogTypeService.removeById(id));
    }

    // 获取全部
    @LogAnnotation()
    @ApiOperation("获取全部分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list() {
        return CommonResult.success(blogTypeService.list());
    }

    // 查看
    @LogAnnotation()
    @ApiOperation("查看分类")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public CommonResult findOne(@PathVariable String id) {
        return CommonResult.success(blogTypeService.getById(id));
    }

}

