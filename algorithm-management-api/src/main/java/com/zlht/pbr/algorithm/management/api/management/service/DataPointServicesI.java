package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.base.BaseServiceI;
import com.zlht.pbr.algorithm.management.dao.chart.LineTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;

import java.util.List;

public interface DataPointServicesI extends BaseServiceI {

    /**
     * 今日用户量,累计用户量
     */
    ValueTypeChart getUserCount(String date);
    /**
     * 今日新增算法,累计算法量
     *
     * @param date
     * @return
     */
    ValueTypeChart getAlgorithmCount(String date);
    /**
     * 开发者提交次数
     *
     * @param date
     * @return
     */
    ValueTypeChart developerCommitCount(String date);
    /**
     * 今日数据集接入,累计数据集
     *
     * @param date
     * @return
     */
    ValueTypeChart getDatasetAccessCount(String date);
    /**
     * 新动作识别类别
     *
     * @param date
     * @return
     */
    ValueTypeChart newActionRecognitionCategory(String date);
    /**
     * 今日接入机构数,累计接入
     *
     * @param date
     * @return
     */
    ValueTypeChart getInstitutionCount(String date);

    /**
     * pie
     * 机构购买算法种类排行
     */
    List<PieTypeChart> getInstitutionAlgorithmRanking();

    /**
     * line
     * 使用识别算法排名Top10的机构
     */
    LineTypeChart getTop10InstitutionAlgorithmUsage();

    /**
     * mutli-line
     * 月用户平均使用时长(分钟)
     */
    LineTypeChart getMonthlyAverageUserUsageDuration();

    /**
     * mutli-line
     * 月度 机构算法种类使用次数
     */
    LineTypeChart getMonthlyInstitutionAlgorithmUsageCount();

}
