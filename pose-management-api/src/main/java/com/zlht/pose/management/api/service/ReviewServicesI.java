package com.zlht.pose.management.api.service;

import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.Review;
import com.zlht.pose.management.dao.entity.User;

import java.util.Map;

public interface ReviewServicesI {

    /**
     * 查询审核信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo> queryReviewList(User loginUser, int currentPage, int pageSize, String keyword);


    /**
     * 查询提交的审核(去重)
     *
     * @return
     */
    Result queryReviewMap(User loginUser);

    /**
     * 更新审核状态
     *
     * @param id
     * @param mark
     * @return
     */
    Map<String, Object> updateReviewStatus(User loginUser, int id, int status, String mark);


    /**
     * 通过审核信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkReviewExistById(int id);

}
