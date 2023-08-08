package com.zlht.pose.management.api.service.impl;


import com.zlht.pose.management.api.service.ChartServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.chart.ChartCollect;
import com.zlht.pose.management.dao.chart.LineTypeChart;
import com.zlht.pose.management.dao.chart.PieTypeChart;
import com.zlht.pose.management.dao.chart.ValueTypeChart;
import com.zlht.pose.management.dao.entity.Charge;
import com.zlht.pose.management.dao.mapper.ChartMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChartServicesImpl extends BaseServiceImpl<Charge> implements ChartServicesI {

    private static final Logger logger = LogManager.getLogger(ChartServicesImpl.class);

    @Autowired
    ChartMapper chartMapper;

    @Override
    public Result getChart(String date) {
        Result result = new Result();

        Map<String, ValueTypeChart> valueTypeChartMap = new HashMap<>();
        Map<String, List<PieTypeChart>> pieChartDataMap = new HashMap<>();
        Map<String, LineTypeChart> lineTypeChartMap = new HashMap<>();
        //value
        valueTypeChartMap.put("getUserCount", getUserCount(date));
        valueTypeChartMap.put("AlgorithmCount", getAlgorithmCount(date));
        valueTypeChartMap.put("getAlgorithmUsageCount", getAlgorithmUsageCount(date));
        valueTypeChartMap.put("DatasetAccessCount", getDatasetAccessCount(date));
        valueTypeChartMap.put("newActionRecognitionCategory", newActionRecognitionCategory(date));
        valueTypeChartMap.put("institutionCount", getInstitutionCount(date));

        //pie

        //line
        ChartCollect chartCollect = ChartCollect.builder()
                .valueTypeChartMap(valueTypeChartMap)
                .pieChartDataMap(pieChartDataMap)
                .lineChartDataMap(lineTypeChartMap)
                .build();
        result.setCode(200);
        result.setMsg("获取图标成功");
        result.setData(chartCollect);
        return result;
    }

    //今日用户量,累计用户量
    ValueTypeChart getUserCount(String date) {
        //TODO
        return new ValueTypeChart("10", "100");
    }


    //今日新增算法,累计算法量
    ValueTypeChart getAlgorithmCount(String date) {
        return chartMapper.getAlgorithmCount(date);
    }

    //今日算法使用量,累计使用量
    ValueTypeChart getAlgorithmUsageCount(String date) {
        //TODO
        return new ValueTypeChart("10", "100");
    }

    //今日数据集接入,累计数据集
    ValueTypeChart getDatasetAccessCount(String date) {
        return chartMapper.getDatasetAccessCount(date);
    }

    //新增动作识别种类,支持识别总数
    ValueTypeChart newActionRecognitionCategory(String date) {
        return chartMapper.newActionRecognitionCategory(date);
    }

    //今日接入机构数,累计接入
    ValueTypeChart getInstitutionCount(String date) {
        return chartMapper.getInstitutionCount(date);
    }

    /**
     * 机构购买算法种类排行
     */
    List<PieTypeChart> getInstitutionAlgorithmRanking() {
        PieTypeChart pieTypeChart = new PieTypeChart();

        return null;
    }

    /**
     * 机构法使用次数排名Top10
     */
    void getTop10InstitutionAlgorithmUsage() {
    }

    /**
     * 月用户平均使用时长(分钟)
     */
    void getMonthlyAverageUserUsageDuration() {
    }

    /**
     * 月机构算法种类使用次
     */
    void getMonthlyInstitutionAlgorithmUsageCount() {
    }
}
