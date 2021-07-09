package cn.njust.label.demo.controller;

import cn.njust.label.common.api.CommonPage;
import cn.njust.label.common.api.CommonResult;
import cn.njust.label.demo.service.DemoService;
import cn.njust.label.demo.dto.UmsAdminDto;
import cn.njust.label.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理示例controller
 */
@Api(tags = "DemoController", description = "品牌管理示例接口")
@Controller
public class DemoController {
    @Autowired
    private DemoService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/admin/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsAdmin>> getAdminList() {
        return CommonResult.success(demoService.listAllAdmin());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createAdmin(@Validated @RequestBody UmsAdminDto pmsAdmin, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = demoService.createAdmin(pmsAdmin);
        if (count == 1) {
            commonResult = CommonResult.success(pmsAdmin);
            LOGGER.debug("createAdmin success:{}", pmsAdmin);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createAdmin failed:{}", pmsAdmin);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateAdmin(@PathVariable("id") Long id, @Validated @RequestBody UmsAdminDto UmsAdminDto, BindingResult result) {
        if(result.hasErrors()){
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = demoService.updateAdmin(id, UmsAdminDto);
        if (count == 1) {
            commonResult = CommonResult.success(UmsAdminDto);
            LOGGER.debug("updateAdmin success:{}", UmsAdminDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateAdmin failed:{}", UmsAdminDto);
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteAdmin(@PathVariable("id") Long id) {
        int count = demoService.deleteAdmin(id);
        if (count == 1) {
            LOGGER.debug("deleteAdmin success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteAdmin failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation(value = "分页获取品牌列表")
    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> listAdmin(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<UmsAdmin> adminList = demoService.listAdmin(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> admin(@PathVariable("id") Long id) {
        return CommonResult.success(demoService.getAdmin(id));
    }
}
