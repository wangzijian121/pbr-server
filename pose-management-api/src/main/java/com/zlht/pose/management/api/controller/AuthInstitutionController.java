package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.AuthInstitutionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.AuthInstitution;
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
@Api(tags = "机构授权管理", description = "机构授权管理")
public class AuthInstitutionController extends BaseController {

    private static final Logger logger = LogManager.getLogger(AuthInstitutionController.class);
    @Autowired
    AuthInstitutionServicesI institutionServices;


    /**
     * 查询机构授权信息
     *
     * @return institution
     */
    @ApiOperation(value = "查询授权的机构", notes = "查询授权的机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auth_type", value = "授权类型:(0：算法授权，1：功能授权)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "institutionName", value = "机构名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<AuthInstitution> queryAuthInstitutionList(@RequestParam(required = false,defaultValue = "-1") int auth_type,
                                                    @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(required = false) String institutionName) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return institutionServices.queryAuthInstitutionList(auth_type, pageNum, pageSize, institutionName);
    }

    /**
     * 创建机构授权
     *
     * @return AuthInstitution
     */
    @ApiOperation(value = "创建机构授权", notes = "创建机构授权")
    @PostMapping(value = "/createAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<AuthInstitution> createAuthInstitution(@RequestBody AuthInstitution authInstitution) {
        return institutionServices.createAuthInstitution(authInstitution);
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
    public Result<AuthInstitution> updateAuthInstitution(@RequestParam int id,
                                                 @RequestBody AuthInstitution authInstitution) {
        return institutionServices.updateAuthInstitution(id, authInstitution);
    }

    /**
     * 删除机构授权
     *
     * @return AuthInstitution
     */
    @ApiOperation(value = "删除机构授权", notes = "删除机构授权")
    @DeleteMapping(value = "/deleteAuthInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<AuthInstitution> deleteAuthInstitution(@RequestParam int id) {
        return institutionServices.deleteAuthInstitution(id);
    }
}
