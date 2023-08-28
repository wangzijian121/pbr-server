package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Commission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface CommissionMapper extends BaseMapper<Commission> {


    @Select("select * from (select c.id, " +
            "dr.id as commitId, " +
            "dr.commit_name as commitName, " +
            "c.review_id as reviewId, " +
            "c.money, " +
            "c.status," +
            "c.mark," +
            "c.create_time as createTime\n" +
            "from commission c\n" +
            "left join developer_review dr on c.review_id = dr.id) res\n" +
            "where ( #{keyword} IS NULL OR res.commitName LIKE CONCAT('%', #{keyword}, '%')) order by id desc")
    Page<Map<String, Object>> selectCommission(Page<?> page, @Param("keyword") String keyword);


    @Select("select * from (" +
            "select c.id, " +
            "dr.id as commitId, " +
            "dr.commit_name as commitName, " +
            "c.review_id as reviewId , " +
            "c.money, " +
            "c.status," +
            " c.mark," +
            " c.create_time as createTime\n" +
            "from commission c\n" +
            "left join developer_review dr on c.review_id = dr.id where dr.developer_id=#{developerId}) res \n" +
            "where ( #{keyword} IS NULL OR res.commitName LIKE CONCAT('%', #{keyword}, '%')) order by id desc")
    Page<Map<String, Object>> selectDeveloperCommission(Page<?> page, @Param("keyword") String keyword,
                                                        @Param("developerId") int developerId);
}
