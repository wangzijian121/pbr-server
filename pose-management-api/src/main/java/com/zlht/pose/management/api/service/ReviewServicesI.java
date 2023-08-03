package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ReviewServicesI {

    /**
     * 查询审核信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryReviewList(int pageNum, int pageSize, String keyword);


    /**
     * 更新审核状态
     *
     * @param id
     * @param mark
     * @return
     */
    Map<String, Object> updateReviewStatus(int id, int status, String mark);

    /**
     * 下载审核资源
     *
     * @param developer_id
     * @param alias
     * @return
     */
    ResponseEntity downloadResource(int  developer_id, String alias);

    /**
     * 通过审核信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkReviewExistById(int id);
}
