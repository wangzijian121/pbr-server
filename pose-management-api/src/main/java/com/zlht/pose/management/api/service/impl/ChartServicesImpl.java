package com.zlht.pose.management.api.service.impl;


import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.ChartServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.chart.ChartCollect;
import com.zlht.pose.management.dao.chart.LineTypeChart;
import com.zlht.pose.management.dao.chart.PieTypeChart;
import com.zlht.pose.management.dao.chart.ValueTypeChart;
import com.zlht.pose.management.dao.entity.Charge;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.ChartMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartServicesImpl extends BaseServiceImpl<Charge> implements ChartServicesI {

    private static final Logger logger = LogManager.getLogger(ChartServicesImpl.class);

    @Autowired
    ChartMapper chartMapper;

    @Override
    public Result getChart(User loginUser, String date) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Map<String, ValueTypeChart> valueTypeChartMap = new HashMap<>();
        Map<String, List<PieTypeChart>> pieChartDataMap = new HashMap<>();
        Map<String, LineTypeChart> lineTypeChartMap = new HashMap<>();
        //value
        valueTypeChartMap.put("user_count", getUserCount(date));
        valueTypeChartMap.put("algorithm_count", getAlgorithmCount(date));
        valueTypeChartMap.put("algorithm_usage_count", getAlgorithmUsageCount(date));
        valueTypeChartMap.put("dataset_access_count", getDatasetAccessCount(date));
        valueTypeChartMap.put("new_action_recognition_category", newActionRecognitionCategory(date));
        valueTypeChartMap.put("institution_count", getInstitutionCount(date));
        //pie
        pieChartDataMap.put("institution_algorithm_ranking", getInstitutionAlgorithmRanking());
        //line
        lineTypeChartMap.put("top10_institution_algorithm_usage", getTop10InstitutionAlgorithmUsage());
        lineTypeChartMap.put("monthly_average_usage_duration", getMonthlyAverageUserUsageDuration());
        lineTypeChartMap.put("monthly_institution_algorithm_usage_count", getMonthlyInstitutionAlgorithmUsageCount());
        ChartCollect chartCollect = ChartCollect.builder()
                .valueTypeChartMap(valueTypeChartMap)
                .pieChartDataMap(pieChartDataMap)
                .lineChartDataMap(lineTypeChartMap)
                .build();
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
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
     * pie
     * 机构购买算法种类排行
     */
    List<PieTypeChart> getInstitutionAlgorithmRanking() {
        return chartMapper.getInstitutionAlgorithmRanking();
    }

    /**
     * line
     * 使用识别算法排名Top10的机构
     */
    LineTypeChart getTop10InstitutionAlgorithmUsage() {
        //TODO
        List<String> xList = Arrays.asList("乐刻健身", "润迪体育", "强力健身", "活力俱乐部", "运动乐园", "健美之家", "动感健身", "魅力体育");

        Map<String, List<String>> mapY = new HashMap<>();
        List<String> yList = Arrays.asList("1240", "690", "590", "240", "189", "100", "35", "12");
        mapY.put("algorithm_usage", yList);
        return new LineTypeChart(xList, mapY);
    }

    /**
     * mutli-line
     * 月用户平均使用时长(分钟)
     */
    LineTypeChart getMonthlyAverageUserUsageDuration() {
        //TODO
        List<String> xList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            xList.add(i + "");
        }

        Map<String, List<String>> mapY = new HashMap<>();
        List<String> lastMonthList = Arrays.asList("88", "5", "13", "10", "6", "12", "11", "6", "2", "12", "19", "12", "14", "9", "12", "34", "6", "2", "1", "11", "5", "2", "5", "7", "9", "8", "7", "6", "9", "14");
        List<String> thisMonthList = Arrays.asList("78", "45", "23", "12", "67", "89", "34", "56", "90", "1", "76", "88", "33", "9", "52", "71", "17", "81", "29", "63", "95", "42", "5", "70", "19", "84", "37", "60", "93", "14");
        mapY.put("last_month_list", lastMonthList);
        mapY.put("this_month_list", thisMonthList);
        return new LineTypeChart(xList, mapY);
    }

    /**
     * mutli-line
     * 月度 机构算法种类使用次数
     */
    LineTypeChart getMonthlyInstitutionAlgorithmUsageCount() {
        List<String> xList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            xList.add(i + "");
        }
        Map<String, List<String>> mapY = new HashMap<>();
        List<String> basketballList = Arrays.asList("12", "6", "7", "9", "5", "11", "1", "14", "2", "34", "2", "6", "7", "8", "6", "9", "5", "10", "12", "11", "88", "9", "19", "13", "12", "5", "6", "2", "12", "14");
        List<String> swimmingList = Arrays.asList("72", "15", "86", "45", "99", "6", "27", "81", "59", "33", "70", "92", "4", "51", "18", "38", "67", "23", "54", "40", "88", "9", "63", "79", "36", "48", "12", "97", "31", "76");
        mapY.put("basketball_list", basketballList);
        mapY.put("swimming_list", swimmingList);
        return new LineTypeChart(xList, mapY);
    }
}
