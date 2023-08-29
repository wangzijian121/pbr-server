package com.zlht.pbr.algorithm.management.api.management.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pbr.algorithm.management.api.management.service.ChargeServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
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
@Api(tags = "收款项管理")
public class ChargeController extends BaseController {

    @Autowired
    private ChargeServicesI chargeServices;


    /**
     * 查询收款项信息
     *
     * @return charge
     */
    @ApiOperation(value = "查询收款项", notes = "查询收款项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "收费项类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "收款项名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getCharge")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Charge>> queryChargeList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                    @RequestParam(required = false, defaultValue = "-1") int type,
                                                    @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return chargeServices.queryChargeList(loginUser, type, currentPage, pageSize, name);
    }

    /**
     * 创建收款项
     *
     * @return Charge
     */
    @ApiOperation(value = "创建收款项", notes = "创建收款项")
    @PostMapping(value = "/createCharge")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Charge> createCharge(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestBody Charge charge) {
        Map<String, Object> map = chargeServices.createCharge(loginUser, charge);
        return returnDataList(map);
    }

    /**
     * 更新收款项
     *
     * @return Charge
     */
    @ApiOperation(value = "更新收款项", notes = "更新收款项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的收款项ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateCharge")
    @ResponseStatus(HttpStatus.OK)
    public Result<Charge> updateCharge(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestParam int id,
                                       @RequestBody Charge charge) {
        Map<String, Object> map = chargeServices.updateCharge(loginUser, id, charge);
        return returnDataList(map);
    }

    /**
     * 删除收款项
     *
     * @return Charge
     */
    @ApiOperation(value = "删除收款项", notes = "删除收款项")
    @DeleteMapping(value = "/deleteCharge")
    @ResponseStatus(HttpStatus.OK)
    public Result<Charge> deleteCharge(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestParam int id) {
        Map<String, Object> map = chargeServices.deleteCharge(loginUser, id);
        return returnDataList(map);
    }
}
