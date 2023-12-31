package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.AlgorithmServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.AlgorithmMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class AlgorithmServicesImpl extends BaseServiceImpl<Algorithm> implements AlgorithmServicesI {

    private static final Logger logger = LogManager.getLogger(AlgorithmServicesImpl.class);

    @Autowired
    private AlgorithmMapper algorithmMapper;

    @Override
    public Result<PageInfo<Algorithm>> queryAlgorithmList(User loginUser, int type, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }

        Page<Algorithm> page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> algorithmPage = algorithmMapper.selectAlgorithm(page, keyword, type);
        logger.info("queryAlgorithmList() method. type={}, currentPage={}, pageSize={},keyword={}", type, currentPage, pageSize, keyword);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(algorithmPage.getRecords());
        result.setData(pageInfo);
        return result;
    }

    @Override
    public Result queryAlgorithmMap(User loginUser) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = algorithmMapper.queryAlgorithmMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createAlgorithm(User loginUser, Algorithm algorithm) {
        Map<String, Object> map = new HashMap<>(16);
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
        algorithm.setUploader(loginUser.getId());
        try {
            algorithmMapper.insert(algorithm);
            putMsg(map, Status.SUCCESS.getCode(), "新建算法成功！");
        } catch (Exception e) {
            String errMsg = "新建算法失败";
            logger.error("createAlgorithm() method .message={}, algorithm={}", errMsg, algorithm, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public Map<String, Object> updateAlgorithm(User loginUser, int id, Algorithm algorithm) {
        Map<String, Object> map = new HashMap<>(16);
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
        try {
            algorithmMapper.update(algorithm, updateWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新算法成功！");
        } catch (Exception e) {
            String errMsg = "更新算法失败";
            logger.error("updateAlgorithm() method .message={}, algorithm={}", errMsg, algorithm, e);
            putMsg(map, 400, errMsg);
        }

        return map;
    }

    @Override
    public Map<String, Object> deleteAlgorithm(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>(16);
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
        try {
            algorithmMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除算法成功！");
        } catch (Exception e) {
            String errMsg = "删除算法失败";
            putMsg(map, 400, errMsg);
            logger.error("deleteAlgorithm() method .message={}, id={}", errMsg, id, e);
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
