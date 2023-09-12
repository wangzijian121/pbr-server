package com.zlht.pbr.algorithm.management.api.wechat.controller;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxReportUserServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "上报学生用户")
public class WxReportUserController extends BaseController {

    @Autowired
    private WxReportUserServiceI wxReportUserServiceI;

    /**
     * 创建微信上报数据
     *
     * @return WxReportData
     */
    @ApiOperation(value = "上报学生用户", notes = "上报学生用户")
    @PostMapping(value = "/wechat/reportUserData")
    @ResponseStatus(HttpStatus.OK)
    public void reportUserData(@RequestBody Map<String, Object> map, @RequestParam int event) {
        wxReportUserServiceI.reportUser(map, event);
    }

}
