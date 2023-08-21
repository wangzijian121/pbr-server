package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.Result;

public interface ChartServicesI {

    /**
     * 查询图表
     */
    Result getChart(User loginUser, String now);
}
