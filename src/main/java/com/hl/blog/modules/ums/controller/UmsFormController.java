package com.hl.blog.modules.ums.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hl.blog.common.api.CommonPage;
import com.hl.blog.common.api.CommonResult;
import com.hl.blog.common.log.LogAnnotation;
import com.hl.blog.modules.ums.dto.FormKeyDTO;
import com.hl.blog.modules.ums.dto.FormPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.hl.blog.modules.ums.service.UmsFormService;
import com.hl.blog.modules.ums.model.UmsForm;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  表单配置表 前端控制器
 * </p>
 *
 * @author hl243695czyn
 * @since 2022-11-16
 */
@RestController
@RequestMapping("/admin/form-design")
@Api(tags = "表单配置", description = "表单配置")
public class UmsFormController {

    @Autowired
    private UmsFormService umsFormService;

    // 分页
    @LogAnnotation()
    @ApiOperation("分页查询")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public CommonResult page(@RequestBody FormPageDTO paramsDTO) {
        Page<UmsForm> formList = umsFormService.pageList(paramsDTO);
        return CommonResult.success(CommonPage.restPage(formList));
    }

    // 新增
    @LogAnnotation()
    @ApiOperation("新增表单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult save(@Valid @RequestBody UmsForm umsForm) {
        return CommonResult.success(umsFormService.save(umsForm));
    }

    // 更新
    @LogAnnotation()
    @ApiOperation("更新表单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Valid @RequestBody UmsForm umsForm) {
        return CommonResult.success(umsFormService.updateById(umsForm));
    }

    // 删除
    @LogAnnotation()
    @ApiOperation("删除表单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable String id) {
        return CommonResult.success( umsFormService.removeById(id));
    }

    // 获取全部
    @LogAnnotation()
    @ApiOperation("获取全部表单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list() {
        return CommonResult.success(umsFormService.list());
    }

    // 查看
    @LogAnnotation()
    @ApiOperation("查看表单")
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public CommonResult findOne(@PathVariable String id) {
        return CommonResult.success(umsFormService.getById(id));
    }

    // 根据key获取表单配置
    @LogAnnotation()
    @ApiOperation("根据key获取表单配置")
    @RequestMapping(value = "/getConfigByKey", method = RequestMethod.POST)
    public CommonResult getConfigByKey(@RequestBody FormKeyDTO formKeyDTO) {
        return CommonResult.success(umsFormService.getConfigByKey(formKeyDTO));
    }

}

