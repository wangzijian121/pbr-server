package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.CommissionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Commission;
import com.zlht.pose.management.dao.mapper.CommissionMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommissionServicesImpl extends BaseServiceImpl<Commission> implements CommissionServicesI {

    private static final Logger logger = LogManager.getLogger(CommissionServicesImpl.class);

    @Autowired
    CommissionMapper commissionMapper;

    @Override
    public Result<Commission> queryCommissionList(int pageNum, int pageSize, String keyword) {

        Result result = new Result();
        Page<Commission> page = new Page<>(pageNum, pageSize);

        Page<Map<String, Object>> commissionPage = commissionMapper.selectCommission(page, keyword);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(commissionPage.getRecords());
        return result;
    }

    @Override
    public Map<String, Object> createCommission(Commission commission) {
        Map<String, Object> map = new HashMap<>();
        //exist?
        if (checkSportExistByReviewId(commission)) {
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
    public Map<String, Object> updateCommission(int id, Commission commission) {
        Map<String, Object> map = new HashMap<>();
        if (!checkCommissionExistById(id)) {
            putMsg(map, 400, "更新的佣金项ID不存在！");
            return map;
        }


        //exist?
        if (checkSportExistByReviewId(commission)) {
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
    public Map<String, Object> deleteCommission(int id) {
        Map<String, Object> map = new HashMap<>();
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
    public boolean checkSportExistByReviewId(Commission commission) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("review_id", commission.getReviewId());
        return commissionMapper.exists(queryWrapper);
    }

    @Override
    public boolean checkCommissionExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return commissionMapper.exists(queryWrapper);
    }

}