package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.Algorithm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface AlgorithmMapper extends BaseMapper<Algorithm> {
    @Select("select *\n" +
            "from (select a.id,\n" +
            "             a.name,\n" +
            "             a.type,\n" +
            "             sc.name as sport_category,\n" +
            "             t.name as template_name,\n" +
            "             a.install_type,\n" +
            "             u.nickname,\n" +
            "             a.file,\n" +
            "             a.docs,\n" +
            "             a.example,\n" +
            "             a.create_time\n" +
            "      from algorithm a\n" +
            "               left join template t on t.id = a.template_id\n" +
            "               left join sport_category sc on a.sport_category = sc.id\n" +
            "               left join user u on a.uploader = u.id) res\n  " +
            "where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.id =#{type})")
    Page<Map<String, Object>> selectAlgorithm(Page<?> page, @Param("keyword") String keyword,
                                           @Param("type") int type);
}

