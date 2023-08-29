package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.DataSetServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.DataSet;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.DataSetMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataSetServicesImpl extends BaseServiceImpl<DataSet> implements DataSetServicesI {

    private static final Logger logger = LogManager.getLogger(DataSetServicesImpl.class);

    @Autowired
    DataSetMapper dataSetMapper;

    @Override
    public Result<PageInfo<DataSet>> queryDataSetList(User loginUser, int type, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<DataSet> page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> dataSetPage = dataSetMapper.selectDataSet(page, keyword, type);
        logger.info("queryDataSetList() method. username={},type={} currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), type, currentPage, pageSize, keyword);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(dataSetPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }


    @Override
    public Map<String, Object> createDataSet(User loginUser, DataSet dataSet) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!validateDataSetName(dataSet)) {
            putMsg(map, 400, "数据集名或昵称不符合规范！");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", dataSet.getName());
        checkWrapper.eq("type", dataSet.getType());

        //exist?
        if (dataSetMapper.exists(checkWrapper)) {
            putMsg(map, 400, "该数据集类型下已存在该数据集！");
            return map;
        }
        dataSet.setUploader(loginUser.getId());
        try {
            dataSetMapper.insert(dataSet);
            putMsg(map, Status.SUCCESS.getCode(), "新建数据集成功！");
        } catch (Exception e) {
            String errMsg = "新建数据集失败";
            logger.error("createDataSet() method .message={}, dataSet={}", errMsg, dataSet, e);
            putMsg(map, 400, errMsg);
        }

        return map;
    }


    @Override
    public Map<String, Object> updateDataSet(User loginUser, int id, DataSet dataSet) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkDataSetExistById(id)) {
            putMsg(map, 400, "更新的数据集ID不存在！");
            return map;
        }

        if (!validateDataSetName(dataSet)) {
            putMsg(map, 400, "数据集名不符合规范！");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", dataSet.getName());
        checkWrapper.eq("type", dataSet.getType());
        checkWrapper.ne("id", id);

        //exist?
        if (dataSetMapper.exists(checkWrapper)) {
            putMsg(map, 400, "该数据集类型下已存在该数据集！");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            dataSetMapper.update(dataSet, queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新数据集成功！");
        } catch (Exception e) {
            String errMsg = "更新数据集失败";
            logger.error("updateDataSet() method .message={}, dataSet={}", errMsg, dataSet, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteDataSet(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkDataSetExistById(id)) {
            putMsg(map, 400, "删除的数据集ID不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            dataSetMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除数据集成功！");
        } catch (Exception e) {
            String errMsg = "删除数据集失败";
            logger.error("deleteDataSet() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public boolean checkDataSetExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return dataSetMapper.exists(queryWrapper);
    }


    private static boolean validateDataSetName(DataSet dataSet) {
        if (dataSet == null) {
            return false;
        }
        String name = dataSet.getName();
        // 校验 dataSet_name不为空，并且没有空格
        if (StringUtils.isBlank(name) || StringUtils.containsWhitespace(name)) {
            return false;
        }
        return true;
    }
}
