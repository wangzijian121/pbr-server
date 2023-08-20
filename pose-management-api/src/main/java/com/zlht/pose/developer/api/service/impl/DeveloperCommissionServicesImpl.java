package com.zlht.pose.developer.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.developer.api.service.DeveloperCommissionServicesI;
import com.zlht.pose.enums.Status;
import com.zlht.pose.management.api.service.CommissionServicesI;
import com.zlht.pose.management.api.service.impl.BaseServiceImpl;
import com.zlht.pose.management.dao.entity.Commission;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.CommissionMapper;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeveloperCommissionServicesImpl extends BaseServiceImpl<Commission> implements DeveloperCommissionServicesI {

    private static final Logger logger = LogManager.getLogger(DeveloperCommissionServicesImpl.class);

    @Autowired
    CommissionMapper commissionMapper;


    @Override
    public Result<PageInfo<Commission>> developerQueryCommissionList(User loginUser, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canCommit(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Commission> page = new Page<>(currentPage, pageSize);

        Page<Map<String, Object>> commissionPage = commissionMapper.selectDeveloperCommission(page, keyword,loginUser.getId());
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(commissionPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }
}
