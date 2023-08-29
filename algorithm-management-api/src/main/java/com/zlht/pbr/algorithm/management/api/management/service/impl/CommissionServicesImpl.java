package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.CommissionServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Commission;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.CommissionMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommissionServicesImpl extends BaseServiceImpl<Commission> implements CommissionServicesI {

    private static final Logger logger = LogManager.getLogger(CommissionServicesImpl.class);

    @Autowired
    CommissionMapper commissionMapper;

    @Override
    public Result<PageInfo<Commission>> queryCommissionList(User loginUser, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Commission> page = new Page<>(currentPage, pageSize);

        Page<Map<String, Object>> commissionPage = commissionMapper.selectCommission(page, keyword);
        logger.info("queryCommissionList() method. username={},currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), currentPage, pageSize, keyword);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(commissionPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createCommission(User loginUser, Commission commission) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("review_id", commission.getReviewId());
        //exist?
        if (commissionMapper.exists(queryWrapper)) {
            putMsg(map, 400, "该佣金项类型下已存在该佣金项！");
            return map;
        }
        try {
            commissionMapper.insert(commission);
            putMsg(map, Status.SUCCESS.getCode(), "新建佣金项成功！");
        } catch (Exception e) {
            String errMsg = "新建佣金项失败";
            logger.error("createCommission() method .message={}, commission={}", errMsg, commission, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public Map<String, Object> updateCommission(User loginUser, int id, Commission commission) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkCommissionExistById(id)) {
            String errMsg = "更新的佣金项ID不存在";
            logger.error("updateCommission() method .message={}, id ={}, commission={}", errMsg, id, commission);
            putMsg(map, 400, errMsg);
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper();
        checkWrapper.eq("review_id", commission.getReviewId());
        checkWrapper.ne("id", id);
        //exist?
        if (commissionMapper.exists(checkWrapper)) {
            putMsg(map, 400, "该佣金项类型下已存在该佣金项！");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            commissionMapper.update(commission, queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新佣金项成功！");
        } catch (Exception e) {
            String errMsg = "更新佣金项失败";
            logger.error("updateCommission() method .message={}, id ={}, commission={}", errMsg, id, commission, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteCommission(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkCommissionExistById(id)) {
            putMsg(map, 400, "删除的佣金项ID不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            commissionMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除佣金项成功！");
        } catch (Exception e) {
            String errMsg = "删除佣金项失败";
            logger.error("deleteCommission() method .message={}, id ={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public boolean checkCommissionExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return commissionMapper.exists(queryWrapper);
    }

}
