package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface ChargeMapper extends BaseMapper<Charge> {

    /**
     * 查询费用
     *
     * @param page
     * @param keyword
     * @param type
     * @return
     */
    @Select("select *\n" +
            "from (select c.id,\n" +
            "             c.type,\n" +
            "             i.id as institutionId,\n" +
            "             i.name as institutionName,\n" +
            "             c.charge_time as chargeTime,\n" +
            "             u.nickname as userId,\n" +
            "             u.nickname as confirmPeople,\n" +
            "             c.confirm_time as confirmTime,\n" +
            "             c.status,\n" +
            "             c.mark,\n" +
            "             c.create_time as createTime\n" +
            "      from charge c\n" +
            "               left join institution i on i.id = c.institution_id\n" +
            "               left join user u on u.id = c.confirm_people) res" +
            "  where (#{keyword} IS NULL OR res.institutionName LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.type =#{type}) order by id desc")
    Page<Map<String, Object>> selectCharge(Page<?> page, @Param("keyword") String keyword,
                                           @Param("type") int type);

}
