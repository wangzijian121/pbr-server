package com.zlht.pbr.algorithm.management.api.management.controller;

import com.zlht.pbr.algorithm.management.api.management.service.AlgorithmServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
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

import java.util.Map;

/**
 * @author ziji Wang
 */
@Api(tags = "算法管理")
public class AlgorithmController extends BaseController {

    private static final Logger logger = LogManager.getLogger(AlgorithmController.class);
    @Autowired
    private AlgorithmServicesI algorithmServices;


    /**
     * 查询算法信息
     *
     * @return algorithm
     */
    @ApiOperation(value = "查询算法", notes = "查询算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "算法类型(0普通算法 1专用算法)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "算法名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Algorithm>> queryAlgorithmList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                          @RequestParam(required = false, defaultValue = "-1") int type,
                                                          @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                          @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return algorithmServices.queryAlgorithmList(loginUser, type, currentPage, pageSize, name);
    }

    /**
     * 查询已添加算法
     *
     * @return institution
     */
    @ApiOperation(value = "查询已添加算法", notes = "查询已添加算法")
    @GetMapping(value = "/getAlgorithmMap")
    @ResponseStatus(HttpStatus.OK)
    public Result queryAlgorithmMap(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return algorithmServices.queryAlgorithmMap(loginUser);
    }

    /**
     * 创建算法
     *
     * @return Algorithm
     */
    @ApiOperation(value = "创建算法", notes = "创建算法")
    @PostMapping(value = "/createAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<Algorithm> createAlgorithm(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                             @RequestBody Algorithm algorithm) {
        Map<String, Object> map = algorithmServices.createAlgorithm(loginUser, algorithm);
        return returnDataList(map);
    }

    /**
     * 更新算法
     *
     * @return Algorithm
     */
    @ApiOperation(value = "更新算法", notes = "更新算法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的算法的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<Algorithm> updateAlgorithm(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                             @RequestParam int id,
                                             @RequestBody Algorithm algorithm) {
        Map<String, Object> map = algorithmServices.updateAlgorithm(loginUser, id, algorithm);
        return returnDataList(map);
    }

    /**
     * 删除算法
     *
     * @return Algorithm
     */
    @ApiOperation(value = "删除算法", notes = "删除算法")
    @DeleteMapping(value = "/deleteAlgorithm")
    @ResponseStatus(HttpStatus.OK)
    public Result<Algorithm> deleteAlgorithm(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                             @RequestParam int id) {
        Map<String, Object> map = algorithmServices.deleteAlgorithm(loginUser, id);
        return returnDataList(map);
    }
}
