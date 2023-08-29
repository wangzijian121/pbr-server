package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Institution;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface InstitutionMapper extends BaseMapper<Institution> {


    /**
     * 查询已添加机构映射
     *
     * @return
     */
    @Select("select id,name from  institution  group by id,name order by id desc")
    List<Map<String, Object>> queryInstitutionMap();
}
