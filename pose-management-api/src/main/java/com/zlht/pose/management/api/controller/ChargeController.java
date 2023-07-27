package com.zlht.pose.management.api.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pose.management.api.service.ChargeServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Charge;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "收款项管理", description = "收款项管理")
public class ChargeController extends BaseController {

    private static final Logger logger = LogManager.getLogger(ChargeController.class);
    @Autowired
    ChargeServicesI chargeServices;


    /**
     * 查询收款项信息
     *
     * @return charge
     */
    @ApiOperation(value = "查询收款项", notes = "查询收款项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "收费项类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "收款项名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getCharge")
    @ResponseStatus(HttpStatus.OK)
    public Result<Charge> queryChargeList(@RequestParam(required = false, defaultValue = "-1") int type,
                                          @RequestParam(required = false, defaultValue = "1") int pageNum,
                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(required = false) String name) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return chargeServices.queryChargeList(type, pageNum, pageSize, name);
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
    public Result<Charge> createCharge(@RequestBody Charge charge) {
        Map<String, Object> map = chargeServices.createCharge(charge);
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
    public Result<Charge> updateCharge(@RequestParam int id,
                                       @RequestBody Charge charge) {
        Map<String, Object> map = chargeServices.updateCharge(id, charge);
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
    public Result<Charge> deleteCharge(@RequestParam int id) {
        Map<String, Object> map = chargeServices.deleteCharge(id);
        return returnDataList(map);
    }
}
