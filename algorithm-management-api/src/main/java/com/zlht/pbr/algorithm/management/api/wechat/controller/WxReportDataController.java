package com.zlht.pbr.algorithm.management.api.wechat.controller;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxReportDataServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.WxReportData;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "微信上报数据管理")
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
    public Result<WxReportData> createWxReportData(@RequestBody WxReportData wxReportData) {
        Map<String, Object> map = wxReportDataServiceI.report(wxReportData);
        return returnDataList(map);
    }
}
