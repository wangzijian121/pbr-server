package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.Algorithm;
import com.zlht.pose.management.dao.entity.DataSet;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface DataSetMapper extends BaseMapper<DataSet> {
    @Select("select *\n" +
            "from (select ds.id,\n" +
            "             ds.name,\n" +
            "             ds.type,\n" +
            "             sc.name as sport_category,\n" +
            "             ds.file,\n" +
            "             ds.demo,\n" +
            "             ds.install_type,\n" +
            "             u.nickname,\n" +
            "             ds.create_time\n" +
            "      from data_set ds\n" +
            "               left join user u on ds.uploader = u.id\n" +
            "               left join sport_category sc on ds.type = sc.id) res\n " +
            "where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.id =#{type})")
    Page<Map<String, Object>> selectDataSet(Page<?> page, @Param("keyword") String keyword,
                                            @Param("type") int type);
}

