package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ziji Wang
 */
public interface AlgorithmMapper extends BaseMapper<Algorithm> {
    /**
     * 查询算法
     *
     * @param page
     * @param keyword
     * @param type
     * @return
     */
    @Select("select *\n" +
            "from (select a.id,\n" +
            "             a.name,\n" +
            "             a.type,\n" +
            "             sc.id as sportCategoryId ,\n" +
            "             sc.name as sportCategoryName,\n" +
            "             t.id as templateId,\n" +
            "             t.name as templateName,\n" +
            "             a.install_type as installType,\n" +
            "             u.nickname,\n" +
            "             a.file as fileUuid,\n" +
            "             r.full_name as fileName,\n" +
            "             a.docs,\n" +
            "             a.example,\n" +
            "             a.url,\n" +
            "             a.create_time as createTime \n" +
            "      from algorithm a\n" +
            "               left join template t on t.id = a.template_id\n" +
            "               left join sport_category sc on a.sport_category = sc.id\n" +
            "               left join user u on a.uploader = u.id\n " +
            "               left join resources r  on r.alias = a.file) res\n  " +
            "where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.type =#{type}) order by id desc ")
    Page<Map<String, Object>> selectAlgorithm(Page<?> page, @Param("keyword") String keyword,
                                              @Param("type") int type);


    /**
     * 查询算法图
     *
     * @return
     */
    @Select("select id,name from  algorithm  group by id,name order by id desc")
    List<Map<String, Object>> queryAlgorithmMap();
}

