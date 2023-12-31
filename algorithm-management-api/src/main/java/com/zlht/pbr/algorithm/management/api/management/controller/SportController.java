package com.zlht.pbr.algorithm.management.api.management.controller;


import com.zlht.pbr.algorithm.management.api.management.service.SportServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Sport;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "体育管理")
public class SportController extends BaseController {

    @Autowired
    private SportServicesI sportServices;


    /**
     * 查询体育信息
     *
     * @return sport
     */
    @ApiOperation(value = "查询体育", notes = "查询体育")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "体育类型(0:学校体育 1:群众体育 2:竞技体育)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "体育名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Sport>> querySportList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                  @RequestParam(required = false, defaultValue = "-1") int type,
                                                  @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                  @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return sportServices.querySportList(loginUser, type, currentPage, pageSize, name);
    }

    /**
     * 查询已添加体育
     *
     * @return sport
     */
    @ApiOperation(value = "查询已添加体育", notes = "查询已添加体育")

    @GetMapping(value = "/getSportMap")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> querySportMap(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return sportServices.querySportMap(loginUser);
    }

    /**
     * 创建体育
     *
     * @return Sport
     */
    @ApiOperation(value = "创建体育", notes = "创建体育")
    @PostMapping(value = "/createSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> createSport(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                     @RequestBody Sport sport) {
        Map<String, Object> map = sportServices.createSport(loginUser, sport);
        return returnDataList(map);
    }

    /**
     * 更新体育
     *
     * @return Sport
     */
    @ApiOperation(value = "更新体育", notes = "更新体育")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的体育的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> updateSport(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                     @RequestParam int id,
                                     @RequestBody Sport sport) {
        Map<String, Object> map = sportServices.updateSport(loginUser, id, sport);
        return returnDataList(map);
    }

    /**
     * 删除体育
     *
     * @return Sport
     */
    @ApiOperation(value = "删除体育", notes = "删除体育")
    @DeleteMapping(value = "/deleteSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> deleteSport(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                     @RequestParam int id) {
        Map<String, Object> map = sportServices.deleteSport(loginUser, id);
        return returnDataList(map);
    }
}
