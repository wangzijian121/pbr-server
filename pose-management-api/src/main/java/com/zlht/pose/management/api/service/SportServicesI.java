package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Sport;

import java.util.Map;

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
     * 查询已添加的体育
     *
     * @return
     */
    Result<Sport> querySportMap();

    /**
     * 创建体育
     *
     * @param sport
     * @return
     */

    Map<String, Object> createSport(Sport sport);

    /**
     * 更新体育
     *
     * @param id
     * @param sport
     * @return
     */
    Map<String, Object> updateSport(int id, Sport sport);

    /**
     * 删除体育
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteSport(int id);


    /**
     * 判断是否已存在
     *
     * @param authInstitution
     * @return
     */
    boolean checkSportExistByNameAndType(Sport authInstitution);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkSportExistById(int id );


}
