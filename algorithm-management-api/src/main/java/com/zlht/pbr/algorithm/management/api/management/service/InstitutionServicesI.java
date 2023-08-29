package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.Institution;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface InstitutionServicesI {

    /**
     * 查询机构
     *
     * @param loginUser
     * @param type
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    Result<PageInfo<Institution>> queryInstitutionList(User loginUser, int type, int currentPage, int pageSize, String name);

    /**
     * 查询已添加的机构(去重)
     *
     * @param loginUser
     * @return
     */
    Result queryInstitutionMap(User loginUser);

    /**
     * 创建机构
     *
     * @param loginUser
     * @param institution
     * @return
     */

    Map<String, Object> createInstitution(User loginUser, Institution institution);

    /**
     * 更新机构
     *
     * @param loginUser
     * @param id
     * @param institution
     * @return
     */
    Map<String, Object> updateInstitution(User loginUser, int id, Institution institution);

    /**
     * 删除机构
     *
     * @param loginUser
     * @param id
     * @return
     */
    Map<String, Object> deleteInstitution(User loginUser, int id);


    /**
     * 通过机构ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkInstitutionExistById(int id);
}
