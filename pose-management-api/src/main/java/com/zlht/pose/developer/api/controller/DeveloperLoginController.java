package com.zlht.pose.developer.api.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zlht.pose.base.BaseController;
import com.zlht.pose.developer.api.service.DeveloperReviewServicesI;
import com.zlht.pose.enums.Status;
import com.zlht.pose.security.impl.AbstractAuthenticator;
import com.zlht.pose.management.api.service.CommissionServicesI;
import com.zlht.pose.management.api.service.ReviewServicesI;
import com.zlht.pose.management.api.service.UserServicesI;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.Commission;
import com.zlht.pose.management.dao.entity.Review;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Api(tags = "开发者登录", description = "开发者登录")
public class DeveloperLoginController extends BaseController {

    private static final Logger logger = LogManager.getLogger(DeveloperLoginController.class);

    @Autowired
    AbstractAuthenticator authenticator;

    /**
     * 开发者-登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "开发者-登录", notes = "开发者-登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/developer/login")
    @ResponseStatus(HttpStatus.OK)
    public Result developerLogin(@RequestParam String username,
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
            map = authenticator.authenticate(response, username, password, ip, 1);
            if (Integer.valueOf(map.get("code").toString()) != 0) {
                return returnDataList(map);
            }
        } catch (Exception e) {
            logger.error(Status.AUTHORIZED_USER_ERROR.getMsg(), e);
        }

        Cookie cookie = new Cookie("sessionId", map.get("data").toString());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return returnDataList(map);
    }
}
