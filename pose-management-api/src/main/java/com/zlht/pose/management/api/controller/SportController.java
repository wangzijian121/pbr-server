package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.SportServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Sport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "体育管理", description = "体育管理")
public class SportController extends BaseController {

    private static final Logger logger = LogManager.getLogger(SportController.class);
    @Autowired
    SportServicesI sportServices;


    /**
     * 查询体育信息
     *
     * @return sport
     */
    @ApiOperation(value = "查询的体育", notes = "查询的体育")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "体育类型(0:学校体育 1:群众体育 2:竞技体育)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "体育名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> querySportList(@RequestParam(required = false, defaultValue = "-1") int type,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum,
                                        @RequestParam(required = false, defaultValue = "10") int pageSize,
                                        @RequestParam(required = false) String name) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return sportServices.querySportList(type, pageNum, pageSize, name);
    }

    /**
     * 创建体育
     *
     * @return Sport
     */
    @ApiOperation(value = "创建体育", notes = "创建体育")
    @PostMapping(value = "/createSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> createSport(@RequestBody Sport sport) {
        return sportServices.createSport(sport);
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
    public Result<Sport> updateSport(@RequestParam int id,
                                     @RequestBody Sport sport) {
        return sportServices.updateSport(id, sport);
    }

    /**
     * 删除体育
     *
     * @return Sport
     */
    @ApiOperation(value = "删除体育", notes = "删除体育")
    @DeleteMapping(value = "/deleteSport")
    @ResponseStatus(HttpStatus.OK)
    public Result<Sport> deleteSport(@RequestParam int id) {
        return sportServices.deleteSport(id);
    }
}
