package com.zlht.pose.management.api.controller;


import com.zlht.pose.management.api.service.ReviewServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Review;
import com.zlht.pose.management.dao.entity.User;
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
            @ApiImplicitParam(name = "pageNum", value = "页数(默认1)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小(默认10)", dataTypeClass = int.class),
            @ApiImplicitParam(name = "name", value = "开发者名", dataTypeClass = String.class)
    })
    @GetMapping(value = "/getReview")
    @ResponseStatus(HttpStatus.OK)
    public Result<Review> queryReviewList(@ApiIgnore @RequestAttribute(value = "session.user") User loginUser,
                                          @RequestParam(required = false, defaultValue = "1") int pageNum,
                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(required = false) String name) {

        Result result = checkPageParams(pageNum, pageSize);
        if (!result.checkResult()) {
            return result;
        }
        return reviewServices.queryReviewList(loginUser, pageNum, pageSize, name);
    }


    /**
     * 更新审核状态
     *
     * @return Review
     */
    @ApiOperation(value = "更新审核状态", notes = "更新审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需要更新状态的审核ID", required = true, dataTypeClass = int.class),
            @ApiImplicitParam(name = "status", value = "审核状态(0:未审核 1：审核通过 2：审核未通过)", required = true, dataTypeClass = int.class)
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
