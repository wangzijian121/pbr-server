package com.zlht.pbr.algorithm.management.api.wechat.controller;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxTemplateServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Template;
import com.zlht.pbr.algorithm.management.utils.Result;
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
@Api(tags = "模板接口")
public class WxTemplateController extends BaseController {

    @Autowired
    private WxTemplateServiceI wxTemplateServiceI;

    /**
     * 获取机构已授权的算法
     *
     * @return WxReportData
     */
    @ApiOperation(value = "模板接口", notes = "模板接口")
    @PostMapping(value = "/wechat/getTemplateByAlgorithmId")
    @ResponseStatus(HttpStatus.OK)
    public Result<Template> getTemplateByAlgorithmId(@RequestParam("algorithmId") int algorithmId) {
        return wxTemplateServiceI.getTemplateByAlgorithmId(algorithmId);
    }
}
