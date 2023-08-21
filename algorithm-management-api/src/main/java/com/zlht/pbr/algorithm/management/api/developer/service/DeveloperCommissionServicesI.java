package com.zlht.pbr.algorithm.management.api.developer.service;

import com.zlht.pbr.algorithm.management.dao.entity.Commission;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

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
