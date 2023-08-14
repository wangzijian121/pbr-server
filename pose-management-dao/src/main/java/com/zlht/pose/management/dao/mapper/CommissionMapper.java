package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.Charge;
import com.zlht.pose.management.dao.entity.Commission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface CommissionMapper extends BaseMapper<Commission> {


    @Select("select * from (select c.id, " +
            "dr.id as commit_id , " +
            "dr.commit_name, " +
            "c.review_id, " +
            "c.money, " +
            "c.status," +
            " c.mark," +
            " c.create_time\n" +
            "from commission c\n" +
            "         left join developer_review dr on c.review_id = dr.id) res " +
            "where   (#{keyword} IS NULL OR res.commit_name LIKE CONCAT('%', #{keyword}, '%'))")
    Page<Map<String, Object>> selectCommission(Page<?> page, @Param("keyword") String keyword);

}
