package com.zlht.pose.management.api.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import com.zlht.pose.management.api.service.ResourceServiceI;
import com.zlht.pose.management.api.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author hj
 */

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    private static final Logger logger = LogManager.getLogger(FileController.class);
    @Autowired
    ResourceServiceI resourceServiceI;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", paramType = "form", value = "文件", dataType = "file", required = true)
    })
    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result upload(@RequestPart("file") MultipartFile file) {
        Map<String, Object> map = resourceServiceI.createResource(file);
        return returnDataList(map);
    }
}

