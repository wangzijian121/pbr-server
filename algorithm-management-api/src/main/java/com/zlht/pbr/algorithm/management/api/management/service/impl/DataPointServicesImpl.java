package com.zlht.pbr.algorithm.management.api.management.service.impl;

import com.zlht.pbr.algorithm.management.api.management.service.DataPointServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.chart.LineTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;
import com.zlht.pbr.algorithm.management.dao.mapper.DataPointMapper;
import com.zlht.pbr.algorithm.management.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataPointServicesImpl extends BaseServiceImpl implements DataPointServicesI {


    @Autowired
    private DataPointMapper dataPointMapper;

    //今日用户量,累计用户量
    public ValueTypeChart getUserCount(String date) {
        return dataPointMapper.getUserCount();
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

        List<String> xList = new ArrayList();
        List<String> yList = new ArrayList();
        Map<String, List<String>> mapY = new HashMap<>();
        List<Map<String, Object>> listMap = dataPointMapper.getTop10InstitutionAlgorithmUsage();
        for (Map<String, Object> map : listMap) {
            xList.add(map.get("xList").toString());
            yList.add(map.get("yList").toString());
        }
        mapY.put("algorithmUsage", yList);

        return new LineTypeChart(xList, mapY);
    }

    /**
     * mutli-line
     * 月用户平均使用时长(分钟)
     */
    public LineTypeChart getMonthlyAverageUserUsageDuration() {
        Date startOfCurrentMonth = TimeUtils.getCurrentMonthRange(new Date()).get("startOfMonth");
        Date endOfCurrentMonth = TimeUtils.getCurrentMonthRange(new Date()).get("endOfMonth");

        Date startOfPreviousMonth = TimeUtils.getPreviousMonthRange(new Date()).get("startOfMonth");
        Date endOfPreviousMonth = TimeUtils.getPreviousMonthRange(new Date()).get("endOfMonth");

        //init
        List<String> xList = new ArrayList<>();
        List<String> currentMonthList = new ArrayList<>(30);
        List<String> previousMonthList = new ArrayList<>(30);
        for (int i = 1; i <= 31; i++) {
            xList.add(i + "");
            currentMonthList.add("0");
            previousMonthList.add("0");
        }
        //current map
        List<Map<String, Object>> mapListCurrent = dataPointMapper.getMonthlyAverageUserUsageDuration(startOfCurrentMonth, endOfCurrentMonth);

        for (Map<String, Object> map : mapListCurrent) {
            int index = (int) map.get("day");
            currentMonthList.set(index - 1, map.get("count").toString());
        }
        List<Map<String, Object>> mapListPrevious = dataPointMapper.getMonthlyAverageUserUsageDuration(startOfPreviousMonth, endOfPreviousMonth);
        for (Map<String, Object> map : mapListPrevious) {
            int index = (int) map.get("day");
            previousMonthList.set(index - 1, map.get("count").toString());
        }
        Map<String, List<String>> mapY = new HashMap<>(2);
        mapY.put("lastMonthList", previousMonthList);
        mapY.put("thisMonthList", currentMonthList);

        return new LineTypeChart(xList, mapY);
    }

    /**
     * mutli-line
     * 机构算法使用次数统计
     */
    public LineTypeChart getMonthlyInstitutionAlgorithmUsageCount() {
        Date startOfCurrentMonth = TimeUtils.getCurrentMonthRange(new Date()).get("startOfMonth");
        Date endOfCurrentMonth = TimeUtils.getCurrentMonthRange(new Date()).get("endOfMonth");

        //init
        List<String> xList = new ArrayList<>();
        List<String> currentMonthList = new ArrayList<>(30);
        for (int i = 1; i <= 31; i++) {
            xList.add(i + "");
            currentMonthList.add("0");
        }
        //current map
        List<Map<String, Object>> mapListCurrent = dataPointMapper.getMonthlyInstitutionAlgorithmUsageCount(startOfCurrentMonth, endOfCurrentMonth);

        for (Map<String, Object> map : mapListCurrent) {
            int index = (int) map.get("day");
            currentMonthList.set(index - 1, map.get("count").toString());
        }

        Map<String, List<String>> mapY = new HashMap<>(2);
        mapY.put("thisMonthList", currentMonthList);
        return new LineTypeChart(xList, mapY);
    }
}
