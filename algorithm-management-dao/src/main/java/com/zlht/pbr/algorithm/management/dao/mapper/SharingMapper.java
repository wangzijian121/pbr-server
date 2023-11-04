package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Sharing;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface SharingMapper extends BaseMapper<Sharing> {


    /**
     * 查询机构管理员分成
     *
     * @param page
     * @param keyword
     * @return
     */
    @Select("select * from (select s.id," +
            " u.nickname ," +
            " u.id as userId ," +
            " s.money," +
            " s.status," +
            " s.screenshot_of_payment as screenshotOfPayment, " +
            " s.mark," +
            " s.create_time as createTime" +
            " from sharing s\n" +
            "         left join user u on u.id = s.user_id ) res " +
            " where ( #{keyword} IS NULL OR res.nickname LIKE CONCAT('%', #{keyword}, '%')) order by id desc")
    Page<Map<String, Object>> querySharing(Page<?> page, @Param("keyword") String keyword);


    /**
     * 获取分成统计信息
     *
     * @return
     */
    @Select("SELECT" +
            "    SUM(money) AS totalSharing," +
            "    SUM(CASE WHEN DATE(create_time) = CURDATE() THEN money ELSE 0 END) AS todaySharing," +
            "    COUNT(DISTINCT user_id) AS sharingCount" +
            " FROM  sharing" +
            " WHERE status = 1;")
    Map<String, Object> querySharingStatistics();
}
