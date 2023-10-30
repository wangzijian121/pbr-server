package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.SharingServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Sharing;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.SharingMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 分成项
 *
 * @author zi jian Wang
 */
@Service
public class SharingServicesImpl extends BaseServiceImpl<Sharing> implements SharingServicesI {

    private static final Logger logger = LogManager.getLogger(SharingServicesImpl.class);

    @Autowired
    private SharingMapper sharingMapper;

    @Override
    public Result<PageInfo<Sharing>> querySharingList(User loginUser, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Sharing> page = new Page<>(currentPage, pageSize);

        Page<Map<String, Object>> sharingPage = sharingMapper.querySharing(page, keyword);
        logger.info("querySharingList() method. username={},currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), currentPage, pageSize, keyword);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(sharingPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createSharing(User loginUser, Sharing sharing) {
        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        try {
            sharingMapper.insert(sharing);
            putMsg(map, Status.SUCCESS.getCode(), "新建分成项成功！");
        } catch (Exception e) {
            String errMsg = "新建分成项失败";
            logger.error("createSharing() method .message={}, sharing={}", errMsg, sharing, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public Map<String, Object> updateSharing(User loginUser, int id, Sharing sharing) {
        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkSharingExistById(id)) {
            String errMsg = "更新的分成项ID不存在";
            putMsg(map, 400, errMsg);
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            sharingMapper.update(sharing, queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新分成项成功！");
        } catch (Exception e) {
            String errMsg = "更新分成项失败";
            logger.error("updateSharing() method .message={}, id ={}, sharing={}", errMsg, id, sharing, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteSharing(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkSharingExistById(id)) {
            putMsg(map, 400, "删除的分成项ID不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            sharingMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除分成项成功！");
        } catch (Exception e) {
            String errMsg = "删除分成项失败";
            logger.error("deleteSharing() method .message={}, id ={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public boolean checkSharingExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return sharingMapper.exists(queryWrapper);
    }

}
