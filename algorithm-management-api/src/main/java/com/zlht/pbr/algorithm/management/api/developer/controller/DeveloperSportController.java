package com.zlht.pbr.algorithm.management.api.developer.controller;


import com.zlht.pbr.algorithm.management.api.developer.service.DeveloperSportServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Sport;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "体育管理")
public class DeveloperSportController extends BaseController {

    @Autowired
    private DeveloperSportServicesI developerSportServicesI;

    /**
     * 查询已添加体育
     *
     * @return sport
     */
    @ApiOperation(value = "查询已添加体育", notes = "查询已添加体育")

    @GetMapping(value = "/developer/getSportMap")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> querySportMap(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return developerSportServicesI.querySportMap(loginUser);
    }
}
