package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.springframework.http.ResponseEntity;

/**
 * @author zi jian Wang
 */
public interface ChartServicesI {

    /**
     * 查询图表
     *
     * @param loginUser
     * @param now
     * @return
     */
    Result getChart(User loginUser, String now);


    /**
     * 下载图表
     *
     * @param loginUser
     * @param date
     * @return
     */
    ResponseEntity downloadChart(User loginUser, String date);
}
