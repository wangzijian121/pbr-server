package com.zlht.pbr.algorithm.management.api.management.controller;


import com.zlht.pbr.algorithm.management.api.management.service.SharingServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Sharing;
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
@Api(tags = "分成项管理")
public class SharingController extends BaseController {

    @Autowired
    private SharingServicesI sharingServices;


    /**
     * 查询分成项信息
     *
     * @return sharing
     */
    @ApiOperation(value = "查询分成项", notes = "查询分成项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "分成项名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getSharing")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Sharing>> querySharingList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                      @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                      @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                      @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return sharingServices.querySharingList(loginUser, currentPage, pageSize, name);
    }


    /**
     * 创建分成项
     *
     * @return Sharing
     */
    @ApiOperation(value = "创建分成项", notes = "创建分成项")
    @PostMapping(value = "/createSharing")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sharing> createSharing(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                         @RequestBody Sharing sharing) {
        Map<String, Object> map = sharingServices.createSharing(loginUser, sharing);
        return returnDataList(map);
    }

    /**
     * 更新分成项
     *
     * @return Sharing
     */
    @ApiOperation(value = "更新分成项", notes = "更新分成项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的分成项的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateSharing")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sharing> updateSharing(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                         @RequestParam int id,
                                         @RequestBody Sharing sharing) {
        Map<String, Object> map = sharingServices.updateSharing(loginUser, id, sharing);
        return returnDataList(map);
    }

    /**
     * 删除分成项
     *
     * @return Sharing
     */
    @ApiOperation(value = "删除分成项", notes = "删除分成项")
    @DeleteMapping(value = "/deleteSharing")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sharing> deleteSharing(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                         @RequestParam int id) {
        Map<String, Object> map = sharingServices.deleteSharing(loginUser, id);
        return returnDataList(map);
    }

    /**
     * 获取分成项统计
     *
     * @return Sharing
     */
    @ApiOperation(value = "获取分成项统计", notes = "获取分成项统计")
    @GetMapping(value = "/querySharingStatistics")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sharing> querySharingStatistics(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return sharingServices.querySharingStatistics(loginUser);
    }
}
