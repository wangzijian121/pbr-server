package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Sport;

public interface SportServicesI {

    /**
     * 查询体育
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<Sport> querySportList(int type, int pageNum, int pageSize, String sportName);

    /**
     * 创建体育
     *
     * @param sport
     * @return
     */

    Result<Sport> createSport(Sport sport);

    /**
     * 更新体育
     *
     * @param id
     * @param authInstitution
     * @return
     */
    Result<Sport> updateSport(int id, Sport authInstitution);

    /**
     * 删除体育
     *
     * @param id
     * @return
     */
    Result<Sport> deleteSport(int id);


    boolean checkSportExist(Sport authInstitution);
}
