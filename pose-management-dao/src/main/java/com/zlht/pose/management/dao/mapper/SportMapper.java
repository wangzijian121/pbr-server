package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.Sport;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SportMapper extends BaseMapper<Sport> {
    @Select("select * from (select sc.id,\n" +
            "       sc.type,\n" +
            "       sc.name,\n" +
            "       sc.user_id,\n" +
            "       u.nickname,\n" +
            "       sc.mark,\n" +
            "       sc.create_time\n" +
            "from sport_category sc\n" +
            "         left join user u on u.id = sc.user_id) res " +
            "where   (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%'))")
    Page<Map<String, Object>> selectSport(Page<?> page, @Param("keyword") String keyword);

    @Select("select id,name from  sport_category  group by id,name")
    List<Map<String, Object>> querySportMap();
}
