package com.zlht.pose.developer.api.controller;


import com.zlht.pose.base.BaseController;
import com.zlht.pose.developer.api.service.DeveloperResourceServiceI;
import com.zlht.pose.management.api.service.ResourceServiceI;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author hj
 */

@RestController
@Api(tags = "开发者-文件管理", description = "开发者-文件管理")
public class DeveloperFileController extends BaseController {

    private static final Logger logger = LogManager.getLogger(DeveloperFileController.class);
    @Autowired
    DeveloperResourceServiceI  developerResourceServiceI;

    @ApiOperation(value = "上传资源", notes = "上传资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件", paramType = "form", value = "文件", dataType = "file", required = true)
    })
    @PostMapping(value = "/developer/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result upload(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                         @RequestPart("file") MultipartFile file) {
        Map<String, Object> map = null;
        try {
            map = developerResourceServiceI.createResource(loginUser, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), "文件上传失败！");
        }
        return returnDataList(map);
    }

    @ApiOperation(value = "开发者-文件管理")
    @ApiImplicitParam(name = "uuid", value = "资源的uuid", paramType = "query", required = true, dataType = "String")
    @GetMapping("/developer/download")
    public ResponseEntity download(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                   @RequestParam String uuid) {
        return developerResourceServiceI.downloadResource(loginUser, uuid);
    }
}

