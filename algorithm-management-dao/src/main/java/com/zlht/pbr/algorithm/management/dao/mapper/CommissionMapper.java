package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.dao.entity.Commission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface CommissionMapper extends BaseMapper<Commission> {


    /**
     * 查询佣金
     *
     * @param page
     * @param keyword
     * @return
     */
    @Select("select * from (select c.id, " +
            "dr.id as commitId, " +
            "dr.commit_name as commitName, " +
            "c.review_id as reviewId, " +
            "c.money, " +
            "c.screenshot_of_payment  as screenshotOfPayment, " +
            "c.status," +
            "c.mark," +
            "c.create_time as createTime" +
            " from commission c " +
            " left join developer_review dr on c.review_id = dr.id) res " +
            " where ( #{keyword} IS NULL OR res.commitName LIKE CONCAT('%', #{keyword}, '%')) order by id desc")
    Page<Map<String, Object>> selectCommission(Page<?> page, @Param("keyword") String keyword);


    /**
     * 查询开发者佣金
     *
     * @param page
     * @param keyword
     * @param developerId
     * @return
     */
    @Select("select * from (" +
            "select c.id, " +
            "dr.id as commitId, " +
            "dr.commit_name as commitName, " +
            "c.review_id as reviewId , " +
            "c.money, " +
            "c.screenshot_of_payment  as screenshotOfPayment, " +
            "c.status," +
            " c.mark," +
            " c.create_time as createTime" +
            " from commission c" +
            " left join developer_review dr on c.review_id = dr.id where dr.developer_id=#{developerId}) res" +
            " where ( #{keyword} IS NULL OR res.commitName LIKE CONCAT('%', #{keyword}, '%')) order by id desc")
    Page<Map<String, Object>> selectDeveloperCommission(Page<?> page, @Param("keyword") String keyword,
                                                        @Param("developerId") int developerId);

    /**
     * 获取佣金统计信息
     *
     * @return
     */
    @Select("SELECT" +
            "    SUM(money) AS totalCommission," +
            "    SUM(CASE WHEN DATE(create_time) = CURDATE() THEN money ELSE 0 END) AS todayCommission," +
            "    COUNT(*) AS reviewCount" +
            " FROM commission;")
    Map<String, Object> querySharingStatistics();
}
