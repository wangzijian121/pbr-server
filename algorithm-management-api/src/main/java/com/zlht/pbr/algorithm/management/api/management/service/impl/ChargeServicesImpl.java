package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.ChargeServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.ChargeMapper;
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
        logger.info("queryChargeList() method. username={},type={}, currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), type, currentPage, pageSize, keyword);
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
        charge.setConfirmPeople(loginUser.getId());
        try {
            chargeMapper.insert(charge);
            putMsg(map, Status.SUCCESS.getCode(), "创建收款项成功！");
        } catch (Exception e) {
            String errMsg = "创建收款项失败";
            logger.error("deleteAlgorithm() method .message={}, charge={}", errMsg, charge, e);
            putMsg(map, 400, errMsg);
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
        charge.setConfirmPeople(loginUser.getId());
        try {
            chargeMapper.update(charge, queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新收款项成功！");
        } catch (Exception e) {
            String errMsg = "更新收款项失败";
            logger.error("updateCharge() method .message={}, id={},charge={}", errMsg, id, charge, e);
            putMsg(map, 400, errMsg);
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
            String errMsg = "所删除的收款项ID不存在";
            logger.error("deleteCharge() method .message={}, id={}", errMsg, id);
            putMsg(map, 400, errMsg);
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            chargeMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除收款项成功！");
        } catch (Exception e) {
            String errMsg = "删除收款项失败";
            logger.error("deleteCharge() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
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
