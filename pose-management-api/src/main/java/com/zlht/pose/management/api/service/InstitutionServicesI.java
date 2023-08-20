package com.zlht.pose.management.api.service;

import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.Institution;
import com.zlht.pose.management.dao.entity.User;

import java.util.Map;

public interface InstitutionServicesI {

    /**
     * 查询机构
     *
     * @param type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo<Institution>> queryInstitutionList(User loginUser, int type, int currentPage, int pageSize, String name);

    /**
     * 查询已添加的机构(去重)
     *
     * @return
     */
    Result queryInstitutionMap(User loginUser);

    /**
     * 创建机构
     *
     * @param institution
     * @return
     */

    Map<String, Object> createInstitution(User loginUser, Institution institution);

    /**
     * 更新机构
     *
     * @param id
     * @param institution
     * @return
     */
    Map<String, Object> updateInstitution(User loginUser, int id, Institution institution);

    /**
     * 删除机构
     *
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
