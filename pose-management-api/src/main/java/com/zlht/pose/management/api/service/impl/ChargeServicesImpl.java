package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.enums.Status;
import com.zlht.pose.management.api.service.ChargeServicesI;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.Charge;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.ChargeMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChargeServicesImpl extends BaseServiceImpl<Charge> implements ChargeServicesI {

    private static final Logger logger = LogManager.getLogger(ChargeServicesImpl.class);

    @Autowired
    ChargeMapper chargeMapper;

    @Override
    public Result<PageInfo<Charge>> queryChargeList(User loginUser, int type, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> chargePage = chargeMapper.selectCharge(page, keyword, type);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(chargePage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createCharge(User loginUser, Charge charge) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        charge.setConfirm_people(loginUser.getId());
        int resNum = chargeMapper.insert(charge);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "创建收款项成功！");
        } else {
            putMsg(map, 400, "创建收款项失败！");
        }
        return map;

    }


    @Override
    public Map<String, Object> updateCharge(User loginUser, int id, Charge charge) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkChargeExistById(id)) {
            putMsg(map, 400, "所更新的收款项ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        charge.setConfirm_people(loginUser.getId());
        int update = chargeMapper.update(charge, queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新收款项成功！");
        } else {
            putMsg(map, 400, "更新收款项失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteCharge(User loginUser, int id) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkChargeExistById(id)) {
            putMsg(map, 400, "所删除的收款项ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = chargeMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除收款项成功！");
        } else {
            putMsg(map, 400, "删除收款项失败！");
        }
        return map;
    }

    @Override
    public boolean checkChargeExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return chargeMapper.exists(queryWrapper);
    }
}
