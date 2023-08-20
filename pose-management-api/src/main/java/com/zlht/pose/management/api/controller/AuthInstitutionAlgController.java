package com.zlht.pose.management.api.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pose.base.BaseController;
import com.zlht.pose.management.api.service.AuthInstitutionAlgServicesI;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.AuthInstitutionAlg;
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
@Api(tags = "机构授权管理", description = "机构授权管理")
public class AuthInstitutionAlgController extends BaseController {

    private static final Logger logger = LogManager.getLogger(AuthInstitutionAlgController.class);
    @Autowired
    AuthInstitutionAlgServicesI institutionServices;


    /**
     * 查询机构授权信息
     *
     * @return institution
     */
    @ApiOperation(value = "查询授权的机构", notes = "查询授权的机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auth_type", value = "授权类型:(0：算法授权，1：功能授权)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "keyword", value = "机构名搜索关键字", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo> queryAuthInstitutionList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                     @RequestParam(required = false, defaultValue = "-1") int auth_type,
                                                     @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                     @RequestParam(required = false) String keyword) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        Result res = institutionServices.queryAuthInstitutionAlgList(loginUser, auth_type, currentPage, pageSize, keyword);
        return res;
    }

    /**
     * 创建机构授权
     *
     * @return AuthInstitution
     */
    @ApiOperation(value = "创建机构授权", notes = "创建机构授权")
    @PostMapping(value = "/createAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<AuthInstitutionAlg> createAuthInstitution(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                            @RequestBody AuthInstitutionAlg authInstitutionAlg) {
        Map<String, Object> map = institutionServices.createAuthInstitution(loginUser, authInstitutionAlg);
        return returnDataList(map);
    }

    /**
     * 更新机构授权
     *
     * @return AuthInstitution
     */
    @ApiOperation(value = "更新机构授权", notes = "更新机构授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的机构授权的ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<AuthInstitutionAlg> updateAuthInstitution(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                            @RequestParam int id,
                                                            @RequestBody AuthInstitutionAlg authInstitutionAlg) {
        Map<String, Object> map = institutionServices.updateAuthInstitution(loginUser, id, authInstitutionAlg);
        return returnDataList(map);
    }

    /**
     * 删除机构授权
     *
     * @return AuthInstitution
     */
    @ApiOperation(value = "删除机构授权", notes = "删除机构授权")
    @DeleteMapping(value = "/deleteAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<AuthInstitutionAlg> deleteAuthInstitution(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                            @RequestParam int id) {
        Map<String, Object> map = institutionServices.deleteAuthInstitution(loginUser, id);
        return returnDataList(map);
    }
}
