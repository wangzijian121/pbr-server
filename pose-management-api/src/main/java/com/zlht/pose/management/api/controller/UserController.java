package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.UserServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Api(tags = "用户管理", description = "用户管理与用户登录校验")
public class UserController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserServicesI userServices;


    /**
     * 查询用户信息
     *
     * @return User
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "用户类型:(0:管理员,1:机构管理员,2: 开发者,3 机构用户)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "nickname", value = "用户昵称", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getUser")
    @ResponseStatus(HttpStatus.OK)
    public Result<User> queryUserList(@RequestParam(required = false, defaultValue = "-1") int type,
                                      @RequestParam(required = false, defaultValue = "1") int pageNum,
                                      @RequestParam(required = false, defaultValue = "10") int pageSize,
                                      @RequestParam(required = false) String nickname) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return userServices.queryUserList(type, pageNum, pageSize, nickname);
    }

    /**
     * 创建用户
     *
     * @return User
     */
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @PostMapping(value = "/createUser")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> createUser(@RequestBody User user) {
        Map<String, Object> map = userServices.createUser(user);
        return map;
    }

    /**
     * 更新用户
     *
     * @return User
     */
    @ApiOperation(value = "更新用户", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的用户ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateUser")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateUser(@RequestParam int id,
                                          @RequestBody User user) {
        Map<String, Object> map = userServices.updateUser(id, user);
        return map;
    }

    /**
     * 删除用户
     *
     * @return User
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping(value = "/deleteUser")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> deleteUser(@RequestParam int userId) {
        Map<String, Object> map = userServices.deleteUser(userId);
        return map;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public Result login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        // user ip check
        String ip = getClientIpAddress(request);
        if (StringUtils.isEmpty(ip)) {
            return error(10125, "获取不到IP！");
        }

        Map<String, Object> map = null;
        try {
            map = userServices.authenticate(username, password, ip);
        } catch (Exception e) {
            logger.error(Status.AUTHORIZED_USER_ERROR.getMsg(), e);
        }
        return returnDataList(map);
    }
}
