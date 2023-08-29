package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.base.BaseServiceI;
import com.zlht.pbr.algorithm.management.dao.chart.LineTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;

import java.util.List;

/**
 * @author zi jian Wang
 */
public interface DataPointServicesI extends BaseServiceI {

    /**
     * 今日用户量,累计用户量
     *
     * @param date
     * @return
     */
    ValueTypeChart getUserCount(String date);

    /**
     * 今日新增算法,累计算法量
     *
     * @param date
     * @return ValueTypeChart
     */
    ValueTypeChart getAlgorithmCount(String date);

    /**
     * 开发者提交次数
     *
     * @param date
     * @return ValueTypeChart
     */
    ValueTypeChart developerCommitCount(String date);

    /**
     * 今日数据集接入,累计数据集
     *
     * @param date
     * @return ValueTypeChart
     */
    ValueTypeChart getDatasetAccessCount(String date);

    /**
     * 新动作识别类别
     *
     * @param date
     * @return ValueTypeChart
     */
    ValueTypeChart newActionRecognitionCategory(String date);

    /**
     * 今日接入机构数,累计接入
     *
     * @param date
     * @return ValueTypeChart
     */
    ValueTypeChart getInstitutionCount(String date);

    /**
     * 机构购买算法种类排行
     *
     * @return List<PieTypeChart>
     */
    List<PieTypeChart> getInstitutionAlgorithmRanking();

    /**
     * 使用识别算法排名Top10的机构
     *
     * @return LineTypeChart
     */
    LineTypeChart getTop10InstitutionAlgorithmUsage();

    /**
     * 月用户平均使用时长(分钟)
     *
     * @return LineTypeChart
     */
    LineTypeChart getMonthlyAverageUserUsageDuration();

    /**
     * 月度 机构算法种类使用次数
     *
     * @return LineTypeChart
     */
    LineTypeChart getMonthlyInstitutionAlgorithmUsageCount();

}
