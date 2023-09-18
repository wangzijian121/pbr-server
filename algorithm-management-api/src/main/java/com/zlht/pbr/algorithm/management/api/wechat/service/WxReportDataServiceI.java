package com.zlht.pbr.algorithm.management.api.wechat.service;

import com.zlht.pbr.algorithm.management.dao.entity.WxReportData;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface WxReportDataServiceI {


    /**
     * 上报微信用户数据
     *
     * @param wxReportData
     * @return Map<String, Object>
     */
    Map<String, Object> report(WxReportData wxReportData);

}