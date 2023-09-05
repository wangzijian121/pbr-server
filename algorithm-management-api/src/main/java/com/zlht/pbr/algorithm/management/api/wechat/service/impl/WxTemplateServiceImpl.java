package com.zlht.pbr.algorithm.management.api.wechat.service.impl;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxTemplateServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Template;
import com.zlht.pbr.algorithm.management.dao.mapper.WxTemplateMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信模板服务
 *
 * @author zijian Wang
 */
@Service
public class WxTemplateServiceImpl extends BaseServiceImpl implements WxTemplateServiceI {

    @Autowired
    private WxTemplateMapper wxTemplateMapper;

    @Override
    public Result<Template> getTemplateByAlgorithmId(int algorithmId) {
        Result result = new Result();
        List<Template> list = wxTemplateMapper.getAlgorithmTemplate(algorithmId);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }
}
