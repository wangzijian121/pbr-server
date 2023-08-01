package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.CommissionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Commission;
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
@Api(tags = "佣金项管理", description = "佣金项管理")
public class CommissionController extends BaseController {

    private static final Logger logger = LogManager.getLogger(CommissionController.class);
    @Autowired
    CommissionServicesI commissionServices;


    /**
     * 查询佣金项信息
     *
     * @return commission
     */
    @ApiOperation(value = "查询佣金项", notes = "查询佣金项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "佣金项名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getCommission")
    @ResponseStatus(HttpStatus.OK)
    public Result<Commission> queryCommissionList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                  @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(required = false) String name) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return commissionServices.queryCommissionList(pageNum, pageSize, name);
    }


    /**
     * 创建佣金项
     *
     * @return Commission
     */
    @ApiOperation(value = "创建佣金项", notes = "创建佣金项")
    @PostMapping(value = "/createCommission")
    @ResponseStatus(HttpStatus.OK)
    public Result<Commission> createCommission(@RequestBody Commission commission) {
        Map<String, Object> map = commissionServices.createCommission(commission);
        return returnDataList(map);
    }

    /**
     * 更新佣金项
     *
     * @return Commission
     */
    @ApiOperation(value = "更新佣金项", notes = "更新佣金项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的佣金项的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateCommission")
    @ResponseStatus(HttpStatus.OK)
    public Result<Commission> updateCommission(@RequestParam int id,
                                               @RequestBody Commission commission) {
        Map<String, Object> map = commissionServices.updateCommission(id, commission);
        return returnDataList(map);
    }

    /**
     * 删除佣金项
     *
     * @return Commission
     */
    @ApiOperation(value = "删除佣金项", notes = "删除佣金项")
    @DeleteMapping(value = "/deleteCommission")
    @ResponseStatus(HttpStatus.OK)
    public Result<Commission> deleteCommission(@RequestParam int id) {
        Map<String, Object> map = commissionServices.deleteCommission(id);
        return returnDataList(map);
    }
}
