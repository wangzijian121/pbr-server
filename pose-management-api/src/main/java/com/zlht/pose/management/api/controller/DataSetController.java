package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.DataSetServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.DataSet;
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
@Api(tags = "数据集管理", description = "数据集管理")
public class DataSetController extends BaseController {

    private static final Logger logger = LogManager.getLogger(DataSetController.class);
    @Autowired
    DataSetServicesI dataSetServices;


    /**
     * 查询数据集信息
     *
     * @return dataSet
     */
    @ApiOperation(value = "查询数据集", notes = "查询数据集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "数据集类型（0:普通数据集 ,1:专用数据集）", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "数据集名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getDataSet")
    @ResponseStatus(HttpStatus.OK)
    public Result<DataSet> queryDataSetList(@RequestParam(required = false, defaultValue = "-1") int type,
                                                @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String name) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return dataSetServices.queryDataSetList(type, pageNum, pageSize, name);
    }

    /**
     * 创建数据集
     *
     * @return DataSet
     */
    @ApiOperation(value = "创建数据集", notes = "创建数据集")
    @PostMapping(value = "/createDataSet")
    @ResponseStatus(HttpStatus.OK)
    public Result<DataSet> createDataSet(@RequestBody DataSet dataSet) {
        Map<String, Object> map = dataSetServices.createDataSet(dataSet);
        return returnDataList(map);
    }

    /**
     * 更新数据集
     *
     * @return DataSet
     */
    @ApiOperation(value = "更新数据集", notes = "更新数据集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的数据集的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateDataSet")
    @ResponseStatus(HttpStatus.OK)
    public Result<DataSet> updateDataSet(@RequestParam int id,
                                             @RequestBody DataSet dataSet) {
        Map<String, Object> map = dataSetServices.updateDataSet(id, dataSet);
        return returnDataList(map);
    }

    /**
     * 删除数据集
     *
     * @return DataSet
     */
    @ApiOperation(value = "删除数据集", notes = "删除数据集")
    @DeleteMapping(value = "/deleteDataSet")
    @ResponseStatus(HttpStatus.OK)
    public Result<DataSet> deleteDataSet(@RequestParam int id) {
        Map<String, Object> map = dataSetServices.deleteDataSet(id);
        return returnDataList(map);
    }
}