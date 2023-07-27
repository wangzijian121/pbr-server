package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Institution;

import java.util.Map;

public interface InstitutionServicesI {

    /**
     * 查询机构
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryInstitutionList(int type, int pageNum, int pageSize, String name);

    /**
     * 查询已添加的机构(去重)
     *
     * @return
     */
    Result queryInstitutionMap();

    /**
     * 创建机构
     *
     * @param institution
     * @return
     */

    Map<String, Object> createInstitution(Institution institution);

    /**
     * 更新机构
     *
     * @param id
     * @param institution
     * @return
     */
    Map<String, Object> updateInstitution(int id, Institution institution);

    /**
     * 删除机构
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteInstitution(int id);


    /**
     * 通过机构名判断是否存在
     *
     * @param Institution
     * @return
     */
    boolean checkInstitutionExistByName(Institution Institution);


    /**
     * 通过机构ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean checkInstitutionExistById(int id);
}
