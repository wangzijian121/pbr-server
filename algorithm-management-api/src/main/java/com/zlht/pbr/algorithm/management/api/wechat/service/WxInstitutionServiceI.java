package com.zlht.pbr.algorithm.management.api.wechat.service;

import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.List;

/**
 * 微信端机构服务
 *
 * @author zi jian Wang
 */
public interface WxInstitutionServiceI {


    /**
     * 获取微信端授权的算法
     *
     * @return
     */
    Result<List<Object>> getInstitutionAlgorithm();

    /**
     * 获取机构的链接码和小程序ID
     *
     * @return
     */
    Result<List<Object>> getInstitutionLinkCodeAndAppId();


}
