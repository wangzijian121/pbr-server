package com.zlht.pose.management.api.service;

import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.User;

public interface ChartServicesI {

    /**
     * 查询图表
     */
    Result getChart(User loginUser, String now);
}
