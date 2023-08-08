package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.chart.ValueTypeChart;
import com.zlht.pose.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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


    @Select("select sum(ress.size) as today, (SELECT sum(ress.size)) AS total\n" +
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

}
