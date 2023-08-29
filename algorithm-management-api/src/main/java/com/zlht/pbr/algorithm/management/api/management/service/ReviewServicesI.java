package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface ReviewServicesI {

    /**
     * 查询审核信息
     *
     * @param loginUser
     * @param currentPage
     * @param pageSize
     * @param keyword
     * @return
     */
    Result<PageInfo> queryReviewList(User loginUser, int currentPage, int pageSize, String keyword);


    /**
     * 查询提交的审核(去重)
     *
     * @param loginUser
     * @return
     */
    Result queryReviewMap(User loginUser);

    /**
     * 更新审核状态
     *
     * @param loginUser
     * @param id
     * @param status
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
