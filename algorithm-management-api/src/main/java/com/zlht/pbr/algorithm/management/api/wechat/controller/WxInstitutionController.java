package com.zlht.pbr.algorithm.management.api.wechat.controller;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxInstitutionServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "微信机构接口")
public class WxInstitutionController extends BaseController {

    @Autowired
    private WxInstitutionServiceI wxReportDataServiceI;

    /**
     * 获取机构已授权的算法
     *
     * @return WxReportData
     */
    @ApiOperation(value = "获取机构已授权的算法", notes = "获取机构已授权的算法")
    @GetMapping(value = "/wechat/getInstitutionAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<Object>> getInstitutionAlgorithm() {
        return wxReportDataServiceI.getInstitutionAlgorithm();
    }


    /**
     * 获取机构链接代码与appID的映射
     *
     * @return WxReportData
     */
    @ApiOperation(value = "获取机构链接代码与appID 的映射", notes = "获取机构链接代码与appID 的映射")
    @GetMapping(value = "/wechat/getInstitutionLinkCodeAndAppId")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<Object>> getInstitutionLinkCodeAndAppId() {
        return wxReportDataServiceI.getInstitutionLinkCodeAndAppId();
    }
}
