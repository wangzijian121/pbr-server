package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.AuthInstitutionAlg;

import java.util.Map;

public interface AuthInstitutionAlgServicesI {

    /**
     * 查询机构
     *
     * @param auth_type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryAuthInstitutionAlgList(int auth_type, int pageNum, int pageSize, String keyword);

    /**
     * 创建机构
     *
     * @param institution
     * @return
     */

    Map<String, Object> createAuthInstitution(AuthInstitutionAlg institution);

    /**
     * 更新机构
     *
     * @param id
     * @param authInstitutionAlg
     * @return
     */
    Map<String, Object> updateAuthInstitution(int id, AuthInstitutionAlg authInstitutionAlg);

    /**
     * 删除机构
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteAuthInstitution(int id);

    boolean checkAuthDuplication(AuthInstitutionAlg authInstitutionAlg);

    boolean checkAuthExistById(int id);

}
