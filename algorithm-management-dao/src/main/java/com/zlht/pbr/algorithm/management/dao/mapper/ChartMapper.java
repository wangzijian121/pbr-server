package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.chart.PieTypeChart;
import com.zlht.pbr.algorithm.management.dao.chart.ValueTypeChart;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChartMapper extends BaseMapper<Charge> {

    @Select("SELECT COUNT(*)   AS today,\n" +
            "       (SELECT COUNT(*) FROM user) AS total\n" +
            "FROM user\n" +
            "WHERE DATE(create_time) = #{date}")
    ValueTypeChart getUserCount(@Param("date") String date);

    @Select("select count(*) as today, (SELECT COUNT(*) FROM user) AS total\n" +
            "from algorithm\n" +
            "WHERE DATE(create_time) = #{date}")
    ValueTypeChart getAlgorithmCount(@Param("date") String date);


    @Select("select COALESCE(CEIL(SUM(ress.size) / 1024),0) as today,COALESCE(CEIL((SELECT SUM(ress.size) / 1024)),0) AS total\n" +
            "from (select  size ,ds.create_time\n" +
            "      from data_set ds\n" +
            "               left join resources r on r.alias = ds.file) ress\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart getDatasetAccessCount(@Param("date") String date);


    @Select("select count(*) as today, (SELECT COUNT(*) FROM user) AS total\n" +
            "from sport_category\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart newActionRecognitionCategory(@Param("date") String date);


    @Select("select count(*) as today, (SELECT COUNT(*) FROM user) AS total\n" +
            "from institution\n" +
            "WHERE DATE(create_time) = #{date};")
    ValueTypeChart getInstitutionCount(@Param("date") String date);


    @Select("select a.name as name, count(auth_alg_id) as value\n" +
            "from auth_institution_alg aia\n" +
            "         left join institution i on i.id = aia.institution_id\n" +
            "         left join algorithm a on a.id = aia.auth_alg_id\n" +
            "         left join sport_category sc on a.sport_category = sc.id\n" +
            "group by auth_alg_id;")
    List<PieTypeChart> getInstitutionAlgorithmRanking();

}
