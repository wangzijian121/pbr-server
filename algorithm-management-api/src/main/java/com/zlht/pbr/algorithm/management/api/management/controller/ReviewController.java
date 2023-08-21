package com.zlht.pbr.algorithm.management.api.management.controller;


import com.zlht.pbr.algorithm.management.api.management.service.ReviewServicesI;
import com.zlht.pbr.algorithm.management.base.BaseController;
import com.zlht.pbr.algorithm.management.dao.entity.Review;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
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
@Api(tags = "开发者审核管理", description = "开发者审核管理")
public class ReviewController extends BaseController {

    private static final Logger logger = LogManager.getLogger(ReviewController.class);
    @Autowired
    ReviewServicesI reviewServices;


    /**
     * 查询审核信息
     *
     * @return review
     */
    @ApiOperation(value = "查询审核", notes = "查询审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "开发者名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getReview")
    @ResponseStatus(HttpStatus.OK)
    public Result<PageInfo> queryReviewList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                            @RequestParam(required = false, defaultValue = "1") int currentPage,
                                            @RequestParam(required = false, defaultValue = "10") int pageSize,
                                            @RequestParam(required = false) String name) {

        Result result = checkPageParams(currentPage, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return reviewServices.queryReviewList(loginUser, currentPage, pageSize, name);
    }

    /**
     * 查询已添加审核
     *
     * @return review
     */
    @ApiOperation(value = "查询已添加审核", notes = "查询已添加审核")
    @GetMapping(value = "/getReviewMap")
    @ResponseStatus(HttpStatus.OK)
    public Result getReviewMap(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser) {
        return reviewServices.queryReviewMap(loginUser);
    }

    /**
     * 更新审核状态
     *
     * @return Review
     */
    @ApiOperation(value = "更新审核状态", notes = "更新审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新状态的审核ID", required = true, dataTypeClass = int.class),
            @ApiImplicitParam(name = "status", value = "审核状态（0：未提交 1：已提交未审核 2： 已提交未通过 3： 已提交已通过）", required = true, dataTypeClass = int.class)
    })
    @PutMapping(value = "/updateReviewStatus")
    @ResponseStatus(HttpStatus.OK)
    public Result<Review> updateReview(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                       @RequestParam int id,
                                       @RequestParam int status,
                                       @RequestBody String mark) {
        Map<String, Object> map = reviewServices.updateReviewStatus(loginUser, id, status, mark);
        return returnDataList(map);
    }

}
