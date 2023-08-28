package com.zlht.pbr.algorithm.management.api.management.service.impl;

import com.zlht.pbr.algorithm.management.api.management.service.DataPointServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.chart.LineTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;
import com.zlht.pbr.algorithm.management.dao.mapper.DataPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataPointServicesImpl extends BaseServiceImpl implements DataPointServicesI {


    @Autowired
    DataPointMapper dataPointMapper;

    //今日用户量,累计用户量
    public ValueTypeChart getUserCount(String date) {
        //TODO
        return new ValueTypeChart("10", "100");
    }
    //今日新增算法,累计算法量
    public ValueTypeChart getAlgorithmCount(String date) {
        return dataPointMapper.getAlgorithmCount(date);
    }

    //开发者提交量
    public ValueTypeChart developerCommitCount(String date) {
        return dataPointMapper.getDeveloperCommitCount(date);
    }
    //今日数据集接入,累计数据集
    public ValueTypeChart getDatasetAccessCount(String date) {
        return dataPointMapper.getDatasetAccessCount(date);
    }

    //新动作识别类别
    public ValueTypeChart newActionRecognitionCategory(String date) {
        //TODO
        return dataPointMapper.newActionRecognitionCategory(date);
    }
    //今日接入机构数,累计接入
    public ValueTypeChart getInstitutionCount(String date) {
        return dataPointMapper.getInstitutionCount(date);
    }

    /**
     * pie
     * 机构购买算法种类排行
     */
    public List<PieTypeChart> getInstitutionAlgorithmRanking() {
        return dataPointMapper.getInstitutionAlgorithmRanking();
    }

    /**
     * line
     * 使用识别算法排名Top10的机构
     */
    public LineTypeChart getTop10InstitutionAlgorithmUsage() {
        //TODO
        List<String> xList = Arrays.asList("乐刻健身", "润迪体育", "强力健身", "活力俱乐部", "运动乐园", "健美之家", "动感健身", "魅力体育");

        Map<String, List<String>> mapY = new HashMap<>();
        List<String> yList = Arrays.asList("1240", "690", "590", "240", "189", "100", "35", "12");
        mapY.put("algorithmUsage", yList);
        return new LineTypeChart(xList, mapY);
    }

    /**
     * mutli-line
     * 月用户平均使用时长(分钟)
     */
    public LineTypeChart getMonthlyAverageUserUsageDuration() {
        //TODO
        List<String> xList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            xList.add(i + "");
        }

        Map<String, List<String>> mapY = new HashMap<>();
        List<String> lastMonthList = Arrays.asList("88", "5", "13", "10", "6", "12", "11", "6", "2", "12", "19", "12", "14", "9", "12", "34", "6", "2", "1", "11", "5", "2", "5", "7", "9", "8", "7", "6", "9", "14");
        List<String> thisMonthList = Arrays.asList("78", "45", "23", "12", "67", "89", "34", "56", "90", "1", "76", "88", "33", "9", "52", "71", "17", "81", "29", "63", "95", "42", "5", "70", "19", "84", "37", "60", "93", "14");
        mapY.put("lastMonthList", lastMonthList);
        mapY.put("thisMonthList", thisMonthList);
        return new LineTypeChart(xList, mapY);
    }

    /**
     * mutli-line
     * 月度 机构算法种类使用次数
     */
    public LineTypeChart getMonthlyInstitutionAlgorithmUsageCount() {
        List<String> xList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            xList.add(i + "");
        }
        Map<String, List<String>> mapY = new HashMap<>();
        List<String> basketballList = Arrays.asList("12", "6", "7", "9", "5", "11", "1", "14", "2", "34", "2", "6", "7", "8", "6", "9", "5", "10", "12", "11", "88", "9", "19", "13", "12", "5", "6", "2", "12", "14");
        List<String> swimmingList = Arrays.asList("72", "15", "86", "45", "99", "6", "27", "81", "59", "33", "70", "92", "4", "51", "18", "38", "67", "23", "54", "40", "88", "9", "63", "79", "36", "48", "12", "97", "31", "76");
        mapY.put("篮球", basketballList);
        mapY.put("游泳", swimmingList);
        return new LineTypeChart(xList, mapY);
    }
}
