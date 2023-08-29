package com.zlht.pbr.algorithm.management.api.management.controller;


import com.zlht.pbr.algorithm.management.api.management.service.ResourceServiceI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.Result;
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
@RequestMapping("/file")
@Api(tags = "文件管理")
public class FileController extends BaseController {

    private static final Logger logger = LogManager.getLogger(FileController.class);
    @Autowired
    private ResourceServiceI resourceServiceI;

    @ApiOperation(value = "上传资源", notes = "上传资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件", paramType = "form", value = "文件", dataType = "file", required = true)
    })
    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result upload(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                         @RequestPart("file") MultipartFile file) {
        Map<String, Object> map = null;
        try {
            map = resourceServiceI.createResource(loginUser, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), "文件上传失败！");
        }
        return returnDataList(map);
    }

    @ApiOperation(value = "删除资源", notes = "删除资源")

    @DeleteMapping(value = "/delete")
    public Result delete(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                         @RequestParam String uuid) {
        Map<String, Object> map = null;
        try {
            map = resourceServiceI.deleteResource(loginUser, uuid);
        } catch (Exception e) {
            logger.error(e.getMessage(), "文件上传失败！");
        }
        return returnDataList(map);
    }

    @ApiOperation(value = "下载接口")
    @ApiImplicitParam(name = "uuid", value = "资源的uuid", paramType = "query", required = true, dataType = "String")
    @GetMapping("/download")
    public ResponseEntity download(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                   @RequestParam String uuid) {
        return resourceServiceI.downloadResource(loginUser, uuid);
    }
}

