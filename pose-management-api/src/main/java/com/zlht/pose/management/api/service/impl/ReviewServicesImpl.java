package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.ReviewServicesI;
import com.zlht.pose.management.api.utils.PageInfo;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Review;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.ReviewMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServicesImpl extends BaseServiceImpl<Review> implements ReviewServicesI {

    private static final Logger logger = LogManager.getLogger(ReviewServicesImpl.class);

    @Autowired
    ReviewMapper reviewMapper;

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

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkReviewExistById(id)) {
            putMsg(map, 400, "所更新的审核ID不存在!");
            return map;
        }
        int update = reviewMapper.updateReviewStatus(id, status, mark);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新审核状态成功！");
        } else {
            putMsg(map, 400, "更新审核状态失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> developCommitReview(User loginUser, Review review) {
        Map<String, Object> map = new HashMap<>();
        if (!canCommit(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (checkReviewExistByName(review.getCommitName(), loginUser)) {
            putMsg(map, 400, "所提交审核已经存在！");
            return map;
        }
        int resNum = reviewMapper.insert(review);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "提交审核成功！");
        } else {
            putMsg(map, 400, "提交审核失败！");
        }
        return map;
    }


    @Override
    public boolean checkReviewExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return reviewMapper.exists(queryWrapper);
    }

    @Override
    public boolean checkReviewExistByName(String reviewName, User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commit_name", reviewName);
        queryWrapper.eq("developer_id", user.getId());
        return reviewMapper.exists(queryWrapper);
    }

}
