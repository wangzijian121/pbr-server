package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zi jian Wang
 */
public interface DataPointMapper extends BaseMapper<Charge> {
    /**
     * 获取用户计数
     *
     * @param date
     * @return
     */
    @Select("SELECT COUNT(*)  AS today,\n" +
            "       (SELECT COUNT(*) FROM user) AS total\n" +
            "FROM user\n" +
            "WHERE DATE(create_time) = #{date}")
    ValueTypeChart getUserCount(@Param("date") String date);

    /**
     * 获取算法计数
     *
     * @param date
     * @return
     */
    @Select("select count(*) as today, (SELECT COUNT(*) FROM algorithm) AS total\n" +
            "from algorithm\n" +
            "WHERE DATE(create_time) = #{date}")
    ValueTypeChart getAlgorithmCount(@Param("date") String date);

    /**
     * 获取开发人员提交计数
     *
     * @param date
     * @return
     */
    @Select("select  count(*) as today, (SELECT COUNT(*) FROM developer_review ) AS total from developer_review where DATE(create_time) =#{date};")
    ValueTypeChart getDeveloperCommitCount(@Param("date") String date);

    /**
     * 获取数据集访问计数
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
     * 新体育识别类别
     *
     * @param date
     * @return
     */
    @Select("select count(*) as today, (SELECT COUNT(*) FROM user) AS total\n" +
            "from sport_category\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart newActionRecognitionCategory(@Param("date") String date);

    /**
     * 获取机构数量
     *
     * @param date
     * @return
     */
    @Select("select count(*) as today, (SELECT COUNT(*) FROM institution) AS total\n" +
            "from institution\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart getInstitutionCount(@Param("date") String date);


    /**
     * 获取机构算法排名
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

}
