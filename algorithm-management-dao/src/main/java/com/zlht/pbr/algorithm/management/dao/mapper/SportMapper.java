package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Sport;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface SportMapper extends BaseMapper<Sport> {
    /**
     * 查询体育信息
     *
     * @param page
     * @param keyword
     * @return
     */
    @Select("select * from (select sc.id,\n" +
            "       sc.type,\n" +
            "       sc.name,\n" +
            "       sc.user_id as userId,\n" +
            "       u.nickname,\n" +
            "       sc.mark,\n" +
            "       sc.create_time as createTime\n" +
            "from sport_category sc\n" +
            "         left join user u on u.id = sc.user_id) res " +
            "where   (#{keyword} IS NULL OR res.name LIKE CONCAT('%', #{keyword}, '%')) and  (#{type}  = -1 OR res.type =#{type})  order by id desc")
    Page<Map<String, Object>> selectSport(Page<?> page, @Param("keyword") String keyword, @Param("type") int type);

    /**
     * 查询已添加映射
     *
     * @return
     */
    @Select("select id,name from  sport_category  group by id,name")
    List<Map<String, Object>> querySportMap();
}
