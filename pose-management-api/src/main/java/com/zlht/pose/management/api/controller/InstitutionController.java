package com.zlht.pose.management.api.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pose.management.api.service.InstitutionServicesI;
import com.zlht.pose.management.api.utils.PageInfo;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Institution;
import com.zlht.pose.management.dao.entity.User;
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
    @ApiOperation(value = "查询机构", notes = "查询机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "机构类型:(0：培训机构，1：健身场所)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "机构名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<Institution>> queryInstitutionList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                              @RequestParam(required = false, defaultValue = "-1") int type,
                                                              @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                              @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                              @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return institutionServices.queryInstitutionList(loginUser, type, currentPage, pageSize, name);
    }

    /**
     * 查询已添加机构
     *
     * @return institution
     */
    @ApiOperation(value = "查询已添加机构", notes = "查询已添加机构")
    @GetMapping(value = "/getInstitutionMap")
    @ResponseStatus(HttpStatus.OK)
    public Result queryInstitutionList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return institutionServices.queryInstitutionMap(loginUser);
    }

    /**
     * 创建机构
     *
     * @return Institution
     */
    @ApiOperation(value = "创建机构", notes = "创建机构")
    @PostMapping(value = "/createInstitution")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<Institution> createInstitution(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                 @RequestBody Institution institution) {
        Map<String, Object> map = institutionServices.createInstitution(loginUser, institution);
        return returnDataList(map);
    }

    /**
     * 更新机构
     *
     * @return Institution
     */
    @ApiOperation(value = "更新机构", notes = "更新机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的机构ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> updateInstitution(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                 @RequestParam int id,
                                                 @RequestBody Institution institution) {
        Map<String, Object> map = institutionServices.updateInstitution(loginUser, id, institution);
        return returnDataList(map);
    }

    /**
     * 删除机构
     *
     * @return Institution
     */
    @ApiOperation(value = "删除机构", notes = "删除机构")
    @DeleteMapping(value = "/deleteInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> deleteInstitution(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                 @RequestParam int id) {
        Map<String, Object> map = institutionServices.deleteInstitution(loginUser, id);
        return returnDataList(map);
    }
}
