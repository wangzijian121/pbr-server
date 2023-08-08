package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.chart.ValueTypeChart;
import com.zlht.pose.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface ChartMapper extends BaseMapper<Charge> {


    @Select("SELECT COUNT(*)   AS today,\n" +
            "       (SELECT COUNT(*) FROM user) AS total\n" +
            "FROM user\n" +
            "WHERE DATE(create_time) = #{date}")
    ValueTypeChart getUserCount(@Param("date") String date);

}
