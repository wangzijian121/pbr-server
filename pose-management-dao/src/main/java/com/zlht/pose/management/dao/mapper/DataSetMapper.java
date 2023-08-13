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
            "             sc.id as sport_category_id,\n" +
            "             sc.name as sport_category_name,\n" +
            "             ds.file as file_uuid,\n" +
            "             r.full_name as file_name,\n" +
            "             ds.demo,\n" +
            "             ds.install_type,\n" +
            "             u.id as user_id,\n" +
            "             u.nickname,\n" +
            "             ds.create_time\n" +
            "      from data_set ds\n" +
            "               left join user u on ds.uploader = u.id\n" +
            "               left join resources r  on r.alias = ds.file\n" +
            "               left join sport_category sc on ds.sport_category = sc.id) res\n " +
            "where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.type =#{type})")
    Page<Map<String, Object>> selectDataSet(Page<?> page, @Param("keyword") String keyword,
                                            @Param("type") int type);
}

