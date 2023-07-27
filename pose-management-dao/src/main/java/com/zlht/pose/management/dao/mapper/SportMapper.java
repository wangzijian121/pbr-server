package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.entity.Sport;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SportMapper extends BaseMapper<Sport> {

    @Select("select id,name from  sport_category  group by id,name")
    List<Map<String,Object>> querySportMap();
}
