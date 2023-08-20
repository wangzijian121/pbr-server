package com.zlht.pose.management.api.service;

import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.Charge;
import com.zlht.pose.management.dao.entity.User;

import java.util.Map;

public interface ChargeServicesI {

    /**
     * 查询收费信息
     *
     * @param type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Charge>> queryChargeList(User loginUser, int type, int currentPage, int pageSize, String keyword);


    /**
     * 创建收费信息
     *
     * @param charge
     * @return
     */

    Map<String, Object> createCharge(User loginUser, Charge charge);

    /**
     * 更新收费信息
     *
     * @param id
     * @param charge
     * @return
     */
    Map<String, Object> updateCharge(User loginUser, int id, Charge charge);

    /**
     * 删除收费信息
     *
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
