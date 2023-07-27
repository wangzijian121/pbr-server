package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Charge;

import java.util.Map;

public interface ChargeServicesI {

    /**
     * 查询收费信息
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryChargeList(int type, int pageNum, int pageSize, String keyword);


    /**
     * 创建收费信息
     *
     * @param charge
     * @return
     */

    Map<String, Object> createCharge(Charge charge);

    /**
     * 更新收费信息
     *
     * @param id
     * @param charge
     * @return
     */
    Map<String, Object> updateCharge(int id, Charge charge);

    /**
     * 删除收费信息
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteCharge(int id);
    

    /**
     * 通过收费信息ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkChargeExistById(int id);
}
