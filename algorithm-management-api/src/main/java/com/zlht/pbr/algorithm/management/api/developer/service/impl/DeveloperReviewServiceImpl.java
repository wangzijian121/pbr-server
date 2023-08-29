package com.zlht.pbr.algorithm.management.api.developer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.developer.service.DeveloperReviewServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Review;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.ReviewMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class DeveloperReviewServiceImpl extends BaseServiceImpl<Review> implements DeveloperReviewServicesI {
    @Autowired
    ReviewMapper reviewMapper;


    @Override
    public Map<String, Object> developCommitReview(User loginUser, Review review) {
        Map<String, Object> map = new HashMap<>(3);
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
    public Result<PageInfo> developerQueryReviewList(User loginUser, int currentPage, int pageSize, String keyword, String type) {

        Result result = new Result();
        if (!canCommit(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> reviewPage = reviewMapper.selectDeveloperReview(page, keyword, loginUser.getId(), type);
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
