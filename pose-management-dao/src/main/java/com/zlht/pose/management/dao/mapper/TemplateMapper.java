package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pose.management.dao.entity.Template;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TemplateMapper extends BaseMapper<Template> {
    @Select("select id,name from  template  group by id,name")
    List<Map<String,Object>> queryTemplateMap();
}
