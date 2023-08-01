package com.zlht.pose.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface ReviewMapper extends BaseMapper<Charge> {


    @Select("select *\n" +
            "from (select c.id,\n" +
            "             c.type,\n" +
            "             i.name as institution_name,\n" +
            "             c.charge_time,\n" +
            "             u.nickname as confirm_people,\n" +
            "             c.confirm_time,\n" +
            "             c.status,\n" +
            "             c.mark,\n" +
            "             c.create_time,\n" +
            "      from charge c\n" +
            "               left join institution i on i.id = c.institution_id\n" +
            "               left join user u on u.id = c.confirm_people) res" +
            "  where (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.id =#{type})")
    Page<Map<String, Object>> selectCharge(Page<?> page, @Param("keyword") String keyword,
                                           @Param("type") int type);

}
