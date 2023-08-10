package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.ReviewServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Review;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.ReviewMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReviewServicesImpl extends BaseServiceImpl<Review> implements ReviewServicesI {

    private static final Logger logger = LogManager.getLogger(ReviewServicesImpl.class);

    @Autowired
    ReviewMapper reviewMapper;

    @Override
    public Result queryReviewList(User loginUser, int pageNum, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(pageNum, pageSize);
        Page<Map<String, Object>> reviewPage = reviewMapper.selectReview(page, keyword);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(reviewPage.getRecords());
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
    public boolean checkReviewExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return reviewMapper.exists(queryWrapper);
    }
}
