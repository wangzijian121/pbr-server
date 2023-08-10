package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.CommissionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Commission;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.CommissionMapper;
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
    public Result<Commission> queryCommissionList(User loginUser, int pageNum, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Commission> page = new Page<>(pageNum, pageSize);

        Page<Map<String, Object>> commissionPage = commissionMapper.selectCommission(page, keyword);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(commissionPage.getRecords());
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

        int resNum = commissionMapper.insert(commission);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "新建佣金项成功！");
        } else {
            putMsg(map, 400, "新建佣金项失败！");
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
            putMsg(map, 400, "更新的佣金项ID不存在！");
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
        int update = commissionMapper.update(commission, queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新佣金项成功！");
        } else {
            putMsg(map, 400, "更新佣金项失败！");
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
        int delete = commissionMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除佣金项成功！");
        } else {
            putMsg(map, 400, "删除佣金项失败！");
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
