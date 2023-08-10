package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.AlgorithmServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Algorithm;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.AlgorithmMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlgorithmServicesImpl extends BaseServiceImpl<Algorithm> implements AlgorithmServicesI {

    private static final Logger logger = LogManager.getLogger(AlgorithmServicesImpl.class);

    @Autowired
    AlgorithmMapper algorithmMapper;

    @Override
    public Result<Algorithm> queryAlgorithmList(User loginUser, int type, int pageNum, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Algorithm> page = new Page<>(pageNum, pageSize);
        Page<Map<String, Object>> algorithmPage = algorithmMapper.selectAlgorithm(page, keyword, type);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(algorithmPage.getRecords());
        return result;
    }


    @Override
    public Map<String, Object> createAlgorithm(User loginUser, Algorithm algorithm) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!validateAlgorithmName(algorithm)) {
            putMsg(map, 400, "算法名或昵称不符合规范！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", algorithm.getName());
        queryWrapper.eq("type", algorithm.getType());
        //exist?
        if (algorithmMapper.exists(queryWrapper)) {
            putMsg(map, 400, "该算法类型下已存在该算法！");
            return map;
        }

        int resNum = algorithmMapper.insert(algorithm);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "新建算法成功！");
        } else {
            putMsg(map, 400, "新建算法失败！");
        }
        return map;
    }


    @Override
    public Map<String, Object> updateAlgorithm(User loginUser, int id, Algorithm algorithm) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkAlgorithmExistById(id)) {
            putMsg(map, 400, "更新的算法ID不存在！");
            return map;
        }

        if (!validateAlgorithmName(algorithm)) {
            putMsg(map, 400, "算法名不符合规范！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", algorithm.getName());
        queryWrapper.eq("type", algorithm.getType());
        queryWrapper.ne("id", id);
        //exist?
        if (algorithmMapper.exists(queryWrapper)) {
            putMsg(map, 400, "该算法类型下已存在该算法！");
            return map;
        }

        QueryWrapper updateWrapper = new QueryWrapper();
        updateWrapper.eq("id", id);
        int update = algorithmMapper.update(algorithm, updateWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新算法成功！");
        } else {
            putMsg(map, 400, "更新算法失败！");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAlgorithm(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkAlgorithmExistById(id)) {
            putMsg(map, 400, "删除的算法ID不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = algorithmMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除算法成功！");
        } else {
            putMsg(map, 400, "删除算法失败！");
        }
        return map;
    }


    @Override
    public boolean checkAlgorithmExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return algorithmMapper.exists(queryWrapper);
    }


    private static boolean validateAlgorithmName(Algorithm algorithm) {
        if (algorithm == null) {
            return false;
        }
        String name = algorithm.getName();
        // 校验 algorithm_name不为空，并且没有空格
        if (StringUtils.isBlank(name) || StringUtils.containsWhitespace(name)) {
            return false;
        }
        return true;
    }
}
