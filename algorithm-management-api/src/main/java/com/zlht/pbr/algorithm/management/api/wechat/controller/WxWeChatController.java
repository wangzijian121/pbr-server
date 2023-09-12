package com.zlht.pbr.algorithm.management.api.wechat.controller;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxWeChatServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ziji Wang
 */
@RestController
@Api(tags = "微信小程序接口")
@RequestMapping("/wechat")
public class WxWeChatController extends BaseController {

    @Autowired
    private WxWeChatServiceI weChatServiceI;

    /**
     * 判断是否为admin
     *
     * @return WxReportData
     */
    @ApiOperation(value = "判断是否为admin", notes = "判断是否为admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "openId", dataTypeClass = String.class)
    })
    @PostMapping(value = "/adminOrNot")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<Object>> adminOrNot(@RequestParam String openId) {
        return returnDataList(weChatServiceI.adminOrNot(openId));
    }

}
