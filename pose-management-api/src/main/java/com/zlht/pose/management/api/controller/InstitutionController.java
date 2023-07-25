package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.InstitutionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Institution;
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
@Api(tags = "机构管理", description = "机构管理")
public class InstitutionController extends BaseController {

    private static final Logger logger = LogManager.getLogger(InstitutionController.class);
    @Autowired
    InstitutionServicesI institutionServices;


    /**
     * 查询机构信息
     *
     * @return institution
     */
    @ApiOperation(value = "getInstitution", notes = "查询机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "机构类型:(0：培训机构，1：健身场所)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "机构名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> queryInstitutionList(@RequestParam(required = false,defaultValue = "-1") int type,
                                                    @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(required = false) String name) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return institutionServices.queryInstitutionList(type, pageNum, pageSize, name);
    }

    /**
     * 创建机构
     *
     * @return Institution
     */
    @ApiOperation(value = "createInstitution", notes = "创建机构")
    @PostMapping(value = "/createInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> createInstitution(@RequestBody Institution institution) {
        return institutionServices.createInstitution(institution);
    }

    /**
     * 更新机构
     *
     * @return Institution
     */
    @ApiOperation(value = "updateInstitution", notes = "更新机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的机构ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> updateInstitution(@RequestParam int id,
                                                 @RequestBody Institution institution) {
        return institutionServices.updateInstitution(id, institution);
    }

    /**
     * 删除机构
     *
     * @return Institution
     */
    @ApiOperation(value = "deleteInstitution", notes = "删除机构")
    @DeleteMapping(value = "/deleteInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> deleteInstitution(@RequestParam int id) {
        return institutionServices.deleteInstitution(id);
    }
}
