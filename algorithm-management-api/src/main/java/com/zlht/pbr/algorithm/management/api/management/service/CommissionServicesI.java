package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Commission;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

public interface CommissionServicesI {

    /**
     * 查询佣金
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Commission>> queryCommissionList(User loginUser, int currentPage, int pageSize, String reviewName);

    /**
     * 创建佣金
     *
     * @param commission
     * @return
     */

    Map<String, Object> createCommission(User loginUser, Commission commission);

    /**
     * 更新佣金
     *
     * @param id
     * @param commission
     * @return
     */
    Map<String, Object> updateCommission(User loginUser, int id, Commission commission);

    /**
     * 删除佣金
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteCommission(User loginUser, int id);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkCommissionExistById(int id);


}
