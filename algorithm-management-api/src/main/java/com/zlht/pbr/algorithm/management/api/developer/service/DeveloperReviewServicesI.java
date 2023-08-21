package com.zlht.pbr.algorithm.management.api.developer.service;

import com.zlht.pbr.algorithm.management.dao.entity.Review;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

public interface DeveloperReviewServicesI {


    /**
     * 开发者获取提交的审核
     * @param loginUser
     * @return
     */

    Result<PageInfo> developerQueryReviewList(User loginUser, int currentPage, int pageSize, String keyword);


    /**
     * 开发者提交审核
     *
     * @param loginUser
     * @param review
     * @return
     */
    Map<String, Object> developCommitReview(User loginUser, Review review);

    /**
     * 通过提交者名和提交名判断是否有重复
     *
     * @param reviewName
     * @param user
     * @return
     */
    boolean checkReviewExistByName(String reviewName, User user);


}
