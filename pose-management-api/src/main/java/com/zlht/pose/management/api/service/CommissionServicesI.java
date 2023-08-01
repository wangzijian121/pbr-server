package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Commission;
import com.zlht.pose.management.dao.entity.Sport;

import java.util.Map;

public interface CommissionServicesI {

    /**
     * 查询佣金
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<Commission> queryCommissionList(int pageNum, int pageSize, String reviewName);



    /**
     * 创建佣金
     *
     * @param commission
     * @return
     */

    Map<String, Object> createCommission(Commission commission);

    /**
     * 更新佣金
     *
     * @param id
     * @param commission
     * @return
     */
    Map<String, Object> updateCommission(int id, Commission commission);

    /**
     * 删除佣金
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteCommission(int id);

    /**
     * 判断佣金项是否已存在
     *
     * @param commission
     * @return
     */
    boolean checkSportExistByReviewId(Commission commission);


    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkCommissionExistById(int id );


}
