package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.entity.Institution;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface InstitutionMapper extends BaseMapper<Institution> {



    @Select("select id,name from  institution  group by id,name")
    List<Map<String,Object>> queryInstitutionMap();
}
