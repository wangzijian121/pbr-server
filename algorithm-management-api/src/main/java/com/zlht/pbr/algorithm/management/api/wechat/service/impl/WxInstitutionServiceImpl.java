package com.zlht.pbr.algorithm.management.api.wechat.service.impl;

import com.zlht.pbr.algorithm.management.api.wechat.service.WxInstitutionServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import com.zlht.pbr.algorithm.management.dao.mapper.WxInstitutionMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class WxInstitutionServiceImpl extends BaseServiceImpl implements WxInstitutionServiceI {

    @Autowired
    private WxInstitutionMapper wxInstitutionMapper;

    @Override
    public Result<List<Object>> getInstitutionAlgorithm(String appId) {
        Result result = new Result();
        List<Map<String, Object>> list = wxInstitutionMapper.getInstitutionAlgorithm(appId);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }
}
