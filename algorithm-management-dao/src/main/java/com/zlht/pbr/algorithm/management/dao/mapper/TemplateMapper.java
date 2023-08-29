package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Template;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface TemplateMapper extends BaseMapper<Template> {
    /**
     * 查询模板映射
     *
     * @return
     */
    @Select("select id,name from  template  group by id,name order by id desc")
    List<Map<String, Object>> queryTemplateMap();
}
