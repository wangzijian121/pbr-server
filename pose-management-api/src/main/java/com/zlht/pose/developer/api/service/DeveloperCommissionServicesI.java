package com.zlht.pose.developer.api.service;

import com.zlht.pose.management.dao.entity.Commission;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;

import java.util.Map;

public interface DeveloperCommissionServicesI {

    /**
     * 开发者-查询佣金
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Commission>> developerQueryCommissionList(User loginUser, int currentPage, int pageSize, String reviewName);

}
