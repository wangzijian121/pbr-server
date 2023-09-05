package com.zlht.pbr.algorithm.management.api.wechat.service;

import com.zlht.pbr.algorithm.management.base.BaseServiceI;
import com.zlht.pbr.algorithm.management.dao.entity.Template;
import com.zlht.pbr.algorithm.management.utils.Result;

/**
 * @author zi jian Wang
 */
public interface WxTemplateServiceI extends BaseServiceI {


    /**
     * 根据算法ID获取模板
     *
     * @param algorithmId
     * @return
     */
    Result<Template> getTemplateByAlgorithmId(int algorithmId);

}
