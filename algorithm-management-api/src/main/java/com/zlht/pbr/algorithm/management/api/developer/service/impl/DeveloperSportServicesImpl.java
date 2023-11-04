package com.zlht.pbr.algorithm.management.api.developer.service.impl;


import com.zlht.pbr.algorithm.management.api.developer.service.DeveloperSportServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Sport;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.SportMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class DeveloperSportServicesImpl extends BaseServiceImpl<Sport> implements DeveloperSportServicesI {

    private static final Logger logger = LogManager.getLogger(DeveloperSportServicesImpl.class);

    @Autowired
    private SportMapper sportMapper;

    @Override
    public Result<Sport> querySportMap(User loginUser) {
        Result result = new Result();
        if (!canCommit(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = sportMapper.querySportMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }
}
