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
     * 查询用户信息
     *
     * @return Institution
     */
    @ApiOperation(value = "getInstitution", notes = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "用户类型:(0:管理员,1:机构管理员,2: 开发者,3 机构用户)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "nickname", value = "用户昵称", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> queryInstitutionList(@RequestParam int type,
                                                    @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(required = false) String nickname) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return institutionServices.queryInstitutionList(type, pageNum, pageSize, nickname);
    }

    /**
     * 创建用户
     *
     * @return Institution
     */
    @ApiOperation(value = "createInstitution", notes = "创建用户")
    @PostMapping(value = "/createInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> createInstitution(@RequestBody Institution Institution) {
        return institutionServices.createInstitution(Institution);
    }

    /**
     * 更新用户
     *
     * @return Institution
     */
    @ApiOperation(value = "updateInstitution", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的用户ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> updateInstitution(@RequestParam int id,
                                                 @RequestBody Institution Institution) {
        return institutionServices.updateInstitution(id, Institution);
    }

    /**
     * 删除用户
     *
     * @return Institution
     */
    @ApiOperation(value = "deleteInstitution", notes = "删除用户")
    @DeleteMapping(value = "/deleteInstitution")
    @ResponseStatus(HttpStatus.OK)
    public Result<Institution> deleteInstitution(@RequestParam int InstitutionId) {
        return institutionServices.deleteInstitution(InstitutionId);
    }

}
