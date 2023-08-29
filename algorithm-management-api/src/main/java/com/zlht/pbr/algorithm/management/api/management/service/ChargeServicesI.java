package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Charge;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface ChargeServicesI {

    /**
     * 查询收费信息
     *
     * @param loginUser
     * @param type
     * @param currentPage
     * @param pageSize
     * @param keyword
     * @return
     */
    Result<PageInfo<Charge>> queryChargeList(User loginUser, int type, int currentPage, int pageSize, String keyword);


    /**
     * 创建收费信息
     *
     * @param loginUser
     * @param charge
     * @return
     */

    Map<String, Object> createCharge(User loginUser, Charge charge);

    /**
     * 更新收费信息
     *
     * @param loginUser
     * @param id
     * @param charge
     * @return
     */
    Map<String, Object> updateCharge(User loginUser, int id, Charge charge);

    /**
     * 删除收费信息
     *
     * @param loginUser
     * @param id
     * @return
     */
    Map<String, Object> deleteCharge(User loginUser, int id);


    /**
     * 通过收费信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkChargeExistById(int id);
}
