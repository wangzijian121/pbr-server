package com.zlht.pbr.algorithm.management.api.wechat.service;

import com.zlht.pbr.algorithm.management.base.BaseServiceI;

import java.util.Map;

/**
 * 微信端机构服务
 *
 * @author zi jian Wang
 */
public interface WxWeChatServiceI extends BaseServiceI {


    /**
     * 通过链接码和openId判断是否是小程序admin
     *
     * @param openId
     * @return
     */
    Map<String, Object> adminOrNot(String openId);

}
