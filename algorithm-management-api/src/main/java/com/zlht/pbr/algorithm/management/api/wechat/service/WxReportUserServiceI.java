package com.zlht.pbr.algorithm.management.api.wechat.service;

import com.zlht.pbr.algorithm.management.base.BaseServiceI;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface WxReportUserServiceI extends BaseServiceI {


    /**
     * 同步微信用户数据（插入+更新）
     *
     * @param map
     * @param event
     * @return
     */
    void reportUser(Map<String, Object> map, int event);

}
