package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Sharing;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * 分成
 *
 * @author zi jian Wang
 */
public interface SharingServicesI {

    /**
     * 查询分成
     *
     * @param loginUser
     * @param currentPage
     * @param pageSize
     * @param reviewName
     * @return
     */
    Result<PageInfo<Sharing>> querySharingList(User loginUser, int currentPage, int pageSize, String reviewName);

    /**
     * 创建分成
     *
     * @param loginUser
     * @param sharing
     * @return
     */

    Map<String, Object> createSharing(User loginUser, Sharing sharing);

    /**
     * 更新分成
     *
     * @param loginUser
     * @param id
     * @param sharing
     * @return
     */
    Map<String, Object> updateSharing(User loginUser, int id, Sharing sharing);

    /**
     * 删除分成
     *
     * @param loginUser
     * @param id
     * @return
     */
    Map<String, Object> deleteSharing(User loginUser, int id);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkSharingExistById(int id);


}
