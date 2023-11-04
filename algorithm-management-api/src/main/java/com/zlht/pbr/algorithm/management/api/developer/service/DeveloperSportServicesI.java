package com.zlht.pbr.algorithm.management.api.developer.service;

import com.zlht.pbr.algorithm.management.dao.entity.Sport;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.Result;

/**
 * @author zi jian Wang
 */
public interface DeveloperSportServicesI {


    /**
     * 查询已添加的体育
     *
     * @param loginUser
     * @return
     */
    Result<Sport> querySportMap(User loginUser);

}
