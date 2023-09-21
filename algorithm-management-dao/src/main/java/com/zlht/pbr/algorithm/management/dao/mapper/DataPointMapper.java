package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface DataPointMapper extends BaseMapper<Charge> {
    /**
     * 数值-获取用户计数
     *
     * @return
     */
    @Select("select COALESCE((select user_count_today  from  wx_report_data where date(create_time) = curdate()),0)  AS today," +
            " COALESCE(sum(user_count_today),0) AS total" +
            " from wx_report_data " +
            " group by  today")
    ValueTypeChart getUserCount();

    /**
     * 数值-获取算法计数
     *
     * @param date
     * @return
     */
    @Select("select count(*) as today, (SELECT COUNT(*) FROM algorithm) AS total\n" +
            "from algorithm\n" +
            "WHERE DATE(create_time) = #{date}")
    ValueTypeChart getAlgorithmCount(@Param("date") String date);

    /**
     * 数值-获取开发人员提交计数
     *
     * @param date
     * @return
     */
    @Select("select  count(*) as today, (SELECT COUNT(*) FROM developer_review ) AS total from developer_review where DATE(create_time) =#{date};")
    ValueTypeChart getDeveloperCommitCount(@Param("date") String date);

    /**
     * 数值-获取数据集访问计数
     *
     * @param date
     * @return
     */
    @Select("select COALESCE(CEIL(SUM(ress.size) / 1024/1024 ),0) as today,COALESCE(CEIL((SELECT SUM(ress.size) / 1024/1024)),0) AS total\n" +
            "from (select  size ,ds.create_time  \n" +
            "      from data_set ds\n" +
            "               left join resources r on r.alias = ds.file) ress\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart getDatasetAccessCount(@Param("date") String date);

    /**
     * 数值-新体育识别类别
     *
     * @param date
     * @return
     */
    @Select("select count(*) as today, (SELECT COUNT(*) FROM user) AS total\n" +
            "from sport_category\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart newActionRecognitionCategory(@Param("date") String date);

    /**
     * 数值-获取机构数量
     *
     * @param date
     * @return
     */
    @Select("select count(*) as today, (SELECT COUNT(*) FROM institution) AS total\n" +
            "from institution\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart getInstitutionCount(@Param("date") String date);


    /**
     * 饼图-获取机构算法排名
     *
     * @return
     */
    @Select("select a.name as name, count(auth_alg_id) as value\n" +
            "from auth_institution_alg aia\n" +
            "         left join institution i on i.id = aia.institution_id\n" +
            "         left join algorithm a on a.id = aia.auth_alg_id\n" +
            "         left join sport_category sc on a.sport_category = sc.id\n" +
            "group by auth_alg_id order by value  limit 10  ;")
    List<PieTypeChart> getInstitutionAlgorithmRanking();

    /**
     * 柱形图-机构算法使用量Top10
     *
     * @return
     */
    @Select("select i.name as xList,sum(algorithm_count_today) as yList\n" +
            "from wx_report_data wrd\n" +
            "         left join wechat w on wrd.link_code = w.link_code\n" +
            "         left join institution i on i.id = w.institution_id group by i.name")
    List<Map<String, Object>> getTop10InstitutionAlgorithmUsage();


    /**
     * 折线图-获取每月平均用户使用时长
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @Select("select sum(user_usage_time_today) as count, Day(create_time) as day\n" +
            "from wx_report_data\n" +
            "where DATE(create_time) >= #{dateStart}\n" +
            "  and DATE(create_time) <= #{dateEnd}\n" +
            "group by Day(create_time);")
    List<Map<String, Object>> getMonthlyAverageUserUsageDuration(Date dateStart, Date dateEnd);

    /**
     * 折线图-机构算法使用次数统计
     *
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @Select("select sum(algorithm_count_today) as count, Day(create_time) as day\n" +
            "from wx_report_data\n" +
            "where DATE(create_time) >= #{dateStart}\n" +
            "  and DATE(create_time) <= #{dateEnd}\n" +
            "group by Day(create_time);")
    List<Map<String, Object>> getMonthlyInstitutionAlgorithmUsageCount(Date dateStart, Date dateEnd);
}
