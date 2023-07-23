package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.UserServicesI;

import com.zlht.pose.management.api.utils.Result;

import com.zlht.pose.management.dao.entity.User;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "user")
public class UserController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserServicesI userServices;


    /**
     * 查询用户信息
     *
     * @return User
     */
    @ApiOperation(value = "getUser", notes = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "用户类型:(0:管理员,1:机构管理员,2: 开发者,3 机构用户)", required = true, dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, dataTypeClass = int.class)
    })
    @GetMapping(value = "/getUser")
    @ResponseStatus(HttpStatus.OK)
    public Result<User> queryUserList(@RequestParam int type,
                                      @RequestParam int pageNum,
                                      @RequestParam int pageSize) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return userServices.queryUserList(type, pageNum, pageSize);
    }

    /**
     * 创建用户
     *
     * @return User
     */
    @ApiOperation(value = "createUser", notes = "创建用户")
    @PostMapping(value = "/createUser")
    @ResponseStatus(HttpStatus.OK)
    public Result<User> createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }
    /**
     * 更新用户
     *
     * @return User
     */
    @ApiOperation(value = "updateUser", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的用户ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateUser")
    @ResponseStatus(HttpStatus.OK)
    public Result<User> updateUser(@RequestParam int id,
                                   @RequestBody User user) {
        return userServices.updateUser(id, user);
    }
    //更新用户

    /**
     * 删除用户
     *
     * @return User
     */
    @ApiOperation(value = "deleteUser", notes = "删除用户")
    @DeleteMapping(value = "/deleteUser")
    @ResponseStatus(HttpStatus.OK)
    public Result<User> deleteUser(@RequestBody User user) {
        return userServices.createUser(user);
    }
}
