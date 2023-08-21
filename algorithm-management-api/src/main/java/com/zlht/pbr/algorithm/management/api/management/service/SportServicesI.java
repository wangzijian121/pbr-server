package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Sport;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

public interface SportServicesI {

    /**
     * 查询体育
     *
     * @param type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Sport>> querySportList(User loginUser, int type, int currentPage, int pageSize, String sportName);

    /**
     * 查询已添加的体育
     *
     * @return
     */
    Result<Sport> querySportMap(User loginUser);

    /**
     * 创建体育
     *
     * @param sport
     * @return
     */

    Map<String, Object> createSport(User loginUser, Sport sport);

    /**
     * 更新体育
     *
     * @param id
     * @param sport
     * @return
     */
    Map<String, Object> updateSport(User loginUser, int id, Sport sport);

    /**
     * 删除体育
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteSport(User loginUser, int id);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param id
     * @return
     */
    boolean checkSportExistById(int id);


}
