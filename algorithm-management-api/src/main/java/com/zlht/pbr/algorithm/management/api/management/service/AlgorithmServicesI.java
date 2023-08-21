package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Algorithm;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

public interface AlgorithmServicesI {

    /**
     * 查询算法
     *
     * @param type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Algorithm>> queryAlgorithmList(User loginUser, int type, int currentPage, int pageSize, String algorithmName);

    /**
     * 获取算法映射
     *
     * @param loginUser
     * @return
     */
    Result queryAlgorithmMap(User loginUser);

    /**
     * 创建算法
     *
     * @param algorithm
     * @return
     */

    Map<String, Object> createAlgorithm(User loginUser, Algorithm algorithm);

    /**
     * 更新算法
     *
     * @param id
     * @param algorithm
     * @return
     */
    Map<String, Object> updateAlgorithm(User loginUser, int id, Algorithm algorithm);

    /**
     * 删除算法
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteAlgorithm(User loginUser, int id);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkAlgorithmExistById(int id);
}
