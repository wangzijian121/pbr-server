package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.api.service.impl.UserServicesImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "user")
public class UserController {
    @Autowired
    UserServicesImpl userServicesImpl;


    @ApiOperation(value = "getUser", notes = "查询用户")

    @GetMapping(value = "/getUser")
    @ResponseStatus(HttpStatus.OK)
    public String LoginSuccess() {
        List<User> userList = userServicesImpl.queryAll();
        for (User user : userList) {
            System.out.println(user);
        }
        return "wangzijian";
    }

}
