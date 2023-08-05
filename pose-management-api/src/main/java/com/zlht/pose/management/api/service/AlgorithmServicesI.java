package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Algorithm;

import java.util.Map;

public interface AlgorithmServicesI {

    /**
     * 查询算法
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<Algorithm> queryAlgorithmList(int type, int pageNum, int pageSize, String algorithmName);


    /**
     * 创建算法
     *
     * @param algorithm
     * @return
     */

    Map<String, Object> createAlgorithm(Algorithm algorithm);

    /**
     * 更新算法
     *
     * @param id
     * @param algorithm
     * @return
     */
    Map<String, Object> updateAlgorithm(int id, Algorithm algorithm);

    /**
     * 删除算法
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteAlgorithm(int id);


    /**
     * 判断是否已存在
     *
     * @param authInstitution
     * @return
     */
    boolean checkAlgorithmExistByNameAndType(Algorithm authInstitution);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkAlgorithmExistById(int id);
}
