package com.zlht.pbr.algorithm.management.api.wechat.service;

/**
 * @author zi jian Wang
 */
public interface WxReportDataServiceI {


    /**
     * 上报微信用户数据
     *
     * @param linkCode
     * @param type
     * @return Map<String, Object>
     */
    void report(String linkCode, String type, int increment);

}
