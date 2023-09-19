package com.zlht.pbr.algorithm.management.api.wechat.controller;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxReportDataServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "微信数据管理")
public class WxReportDataController extends BaseController {

    @Autowired
    private WxReportDataServiceI wxReportDataServiceI;

    /**
     * 创建微信上报数据
     *
     * @return WxReportData
     */
    @ApiOperation(value = "创建微信上报数据", notes = "创建微信上报数据")
    @PostMapping(value = "/wechat/createWxReportData")
    @ResponseStatus(HttpStatus.OK)
    public void createWxReportData(@RequestParam String linkCode,
                                   @RequestParam String type,
                                   @RequestParam(defaultValue = "1", required = false) int increment) {
        wxReportDataServiceI.report(linkCode, type, increment);
    }

}
