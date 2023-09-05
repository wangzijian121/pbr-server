package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.Template;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ziji Wang
 */
public interface WxTemplateMapper extends BaseMapper<Template> {


    /**
     * 获取算法模板
     *
     * @param id
     * @return
     */
    @Select("SELECT t.*\n" +
            "FROM template t\n" +
            "\tLEFT JOIN algorithm a ON t.id = a.template_id\n" +
            "WHERE a.id = #{id}")
    List<Template> getAlgorithmTemplate(@Param("id") int id);

}

