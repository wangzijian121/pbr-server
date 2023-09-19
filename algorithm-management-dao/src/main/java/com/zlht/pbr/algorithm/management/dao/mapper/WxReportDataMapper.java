package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.WxReportData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author ziji Wang
 */
public interface WxReportDataMapper extends BaseMapper<WxReportData> {

    /**
     * 获取linkCode 今日的数据
     *
     * @param linkCode
     */
    @Select("select *  FROM wx_report_data WHERE DATE(create_time) = CURDATE() and link_code= #{link_code}")
    Map<String, Integer> check(@Param("link_code") String linkCode);

    /**
     * 获取linkCode 今日的数据
     *
     * @param linkCode
     */
    @Select("select *  FROM wx_report_data WHERE DATE(create_time) = CURDATE() and link_code= #{link_code}")
    Map<String, Integer> getLinkCodeTodayData(@Param("link_code") String linkCode);


}

