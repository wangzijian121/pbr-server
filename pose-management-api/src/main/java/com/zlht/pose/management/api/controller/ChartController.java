package com.zlht.pose.management.api.controller;


import com.zlht.pose.base.BaseController;
import com.zlht.pose.management.api.service.ChartServicesI;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;

@RestController
@Api(tags = "图表信息", description = "图表信息")
public class ChartController extends BaseController {

    private static final Logger logger = LogManager.getLogger(ChartController.class);
    @Autowired
    ChartServicesI chartServicesI;


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
}
