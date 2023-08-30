package com.zlht.pbr.algorithm.management.api.management.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pbr.algorithm.management.api.management.service.WeChatServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.entity.WeChat;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author zi jian Wang
 */
@RestController
@Api(tags = "小程序信息管理")
public class WeChatController extends BaseController {

    @Autowired
    private WeChatServicesI weChatServices;


    /**
     * 查询小程序信息信息
     *
     * @return weChat
     */
    @ApiOperation(value = "查询小程序信息", notes = "查询小程序信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "小程序审核进度(0已部署，1审核中)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "keyword", value = "小程序信息名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getWeChat")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo<WeChat>> queryWeChatList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                                    @RequestParam(required = false, defaultValue = "-1") int type,
                                                    @RequestParam(required = false, defaultValue = "1") int currentPage,
                                                    @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                    @RequestParam(required = false) String keyword) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return weChatServices.queryWeChatList(loginUser, currentPage, pageSize, type, keyword);
    }

    /**
     * 创建小程序信息
     *
     * @return WeChat
     */
    @ApiOperation(value = "创建小程序信息", notes = "创建小程序信息")
    @PostMapping(value = "/createWeChat")
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties(value = "id")
    public Result<WeChat> createWeChat(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestBody WeChat weChat) {
        Map<String, Object> map = weChatServices.createWeChat(loginUser, weChat);
        return returnDataList(map);
    }

    /**
     * 更新小程序信息
     *
     * @return WeChat
     */
    @ApiOperation(value = "更新小程序信息", notes = "更新小程序信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新的小程序信息ID", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateWeChat")
    @ResponseStatus(HttpStatus.OK)
    public Result<WeChat> updateWeChat(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestParam int id,
                                       @RequestBody WeChat weChat) {
        Map<String, Object> map = weChatServices.updateWeChat(loginUser, id, weChat);
        return returnDataList(map);
    }

    /**
     * 删除小程序信息
     *
     * @return WeChat
     */
    @ApiOperation(value = "删除小程序信息", notes = "删除小程序信息")
    @DeleteMapping(value = "/deleteWeChat")
    @ResponseStatus(HttpStatus.OK)
    public Result<WeChat> deleteWeChat(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestParam int id) {
        Map<String, Object> map = weChatServices.deleteWeChat(loginUser, id);
        return returnDataList(map);
    }
}
