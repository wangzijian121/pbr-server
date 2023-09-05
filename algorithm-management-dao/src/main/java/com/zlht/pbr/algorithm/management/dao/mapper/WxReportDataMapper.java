package com.zlht.pbr.algorithm.management.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlht.pbr.algorithm.management.dao.entity.WxReportData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @author ziji Wang
 */
public interface WxReportDataMapper extends BaseMapper<WxReportData> {

    /**
     * 删除重复的数据
     *
     * @param appId
     */
    @Delete("DELETE FROM wx_report_data WHERE DATE(create_time) = CURDATE() and app_id= #{appId} ;")
    void deleteDuplicateData(@Param("appId") String appId);
}

