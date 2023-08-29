package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.ReviewServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Review;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.ReviewMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class ReviewServicesImpl extends BaseServiceImpl<Review> implements ReviewServicesI {

    private static final Logger logger = LogManager.getLogger(ReviewServicesImpl.class);

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Result<PageInfo> queryReviewList(User loginUser, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> reviewPage = reviewMapper.selectReview(page, keyword);
        logger.info("queryReviewList() method. username={},currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), currentPage, pageSize, keyword);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(reviewPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Result queryReviewMap(User loginUser) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = reviewMapper.queryReviewMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }

    @Override
    public Map<String, Object> updateReviewStatus(User loginUser, int id, int status, String mark) {

        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkReviewExistById(id)) {
            putMsg(map, 400, "所更新的审核ID不存在!");
            return map;
        }
        try {
            reviewMapper.updateReviewStatus(id, status, mark);
            putMsg(map, Status.SUCCESS.getCode(), "更新审核状态成功！");
        } catch (Exception e) {
            String errMsg = "更新审核状态失败";
            logger.error("updateReviewStatus() method .message={}", errMsg, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public boolean checkReviewExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return reviewMapper.exists(queryWrapper);
    }


}
