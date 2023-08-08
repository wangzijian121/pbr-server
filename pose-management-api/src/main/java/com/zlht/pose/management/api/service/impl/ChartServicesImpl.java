package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.ChargeServicesI;
import com.zlht.pose.management.api.service.ChartServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.chart.ChartCollect;
import com.zlht.pose.management.dao.chart.ValueTypeChart;
import com.zlht.pose.management.dao.entity.Charge;
import com.zlht.pose.management.dao.mapper.ChargeMapper;
import com.zlht.pose.management.dao.mapper.ChartMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChartServicesImpl extends BaseServiceImpl<Charge> implements ChartServicesI {

    private static final Logger logger = LogManager.getLogger(ChartServicesImpl.class);

    @Autowired
    ChartMapper chartMapper;

    @Override
    public ChartCollect getChart(Data now) {

        return null;
    }

    //今日用户量
    //累计用户量
    ValueTypeChart getUserCount(String  date) {
    return  chartMapper.getUserCount(date);
    }


    //今日新增算法
    //累计算法量

    //今日算法使用量
    //累计使用量

    //今日数据集接入
    //累计数据集

    //新增动作识别种类
    //支持识别总数

    //今日接入机构数

    //累计接入


    /**
     *  机构购买算法种类排行
     */

    /**
     *机构法使用次数排名Top10
     */

    /**
     * 月用户平均使用时长(分钟)
     */

    /**
     * 月机构算法种类使用次
     */


    getTodayNewAlgorithmCount()

    getTotalAlgorithmCount()

    getTodayAlgorithmUsageCount()

    getTotalAlgorithmUsageCount()

    getTodayDatasetAccessCount()

    getTotalDatasetCount()

    addNewActionRecognitionCategory()

    getTotalActionRecognitionCount()

    getTodayInstitutionCount()

    getTotalInstitutionCount()

    getInstitutionAlgorithmRanking()

    getTop10InstitutionAlgorithmUsage()

    getMonthlyAverageUserUsageDuration()

    getMonthlyInstitutionAlgorithmUsageCount()

}
