package com.zlht.pose.developer.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.base.BaseServiceI;
import com.zlht.pose.developer.api.service.DeveloperReviewServicesI;
import com.zlht.pose.enums.Status;
import com.zlht.pose.management.api.service.impl.BaseServiceImpl;
import com.zlht.pose.management.dao.entity.Review;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.ReviewMapper;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeveloperReviewServiceImpl extends BaseServiceImpl<Review> implements DeveloperReviewServicesI {
    @Autowired
    ReviewMapper reviewMapper;


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
    public Result<PageInfo> developerQueryReviewList(User loginUser, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canCommit(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> reviewPage = reviewMapper.selectDeveloperReview(page, keyword, loginUser.getId());
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(reviewPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public boolean checkReviewExistByName(String reviewName, User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commit_name", reviewName);
        queryWrapper.eq("developer_id", user.getId());
        return reviewMapper.exists(queryWrapper);
    }

}
