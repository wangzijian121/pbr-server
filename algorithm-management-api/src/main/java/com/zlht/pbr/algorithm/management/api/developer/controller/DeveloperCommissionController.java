package com.zlht.pbr.algorithm.management.api.developer.controller;


import com.zlht.pbr.algorithm.management.api.developer.service.DeveloperCommissionServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Commission;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "开发者-佣金项管理", description = "开发者-佣金项管理")
public class DeveloperCommissionController extends BaseController {

    private static final Logger logger = LogManager.getLogger(DeveloperCommissionController.class);
    @Autowired
    DeveloperCommissionServicesI developerCommissionServicesI;


    /**
     * 查询佣金项信息
     *
     * @return commission
     */
    @ApiOperation(value = "查询佣金项", notes = "查询佣金项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "佣金项名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/developer/getCommission")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Commission>> queryCommissionList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                            @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                            @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return developerCommissionServicesI.developerQueryCommissionList(loginUser, currentPage, pageSize, name);
    }
}
