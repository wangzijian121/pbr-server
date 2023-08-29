package com.zlht.pbr.algorithm.management.api.management.controller;


import com.zlht.pbr.algorithm.management.api.management.service.ChartServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "图表信息")
public class ChartController extends BaseController {

    @Autowired
    private ChartServicesI chartServicesI;


    /**
     * 查询图表信息信息
     *
     * @return weChat
     */
    @ApiOperation(value = "查询图表信息", notes = "查询图表信息")

    @GetMapping(value = "/getChart")
    @ResponseStatus(HttpStatus.OK)
    public Result queryChart(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return chartServicesI.getChart(loginUser, LocalDate.now().toString());
    }


    /**
     * 下载首页图表信息
     *
     * @return weChat
     */
    @ApiOperation(value = "下载首页图表信息", notes = "下载首页图表信息")

    @GetMapping(value = "/downloadChart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity downloadChart(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) throws IOException {
        return chartServicesI.downloadChart(loginUser, LocalDate.now().toString());
    }
}
