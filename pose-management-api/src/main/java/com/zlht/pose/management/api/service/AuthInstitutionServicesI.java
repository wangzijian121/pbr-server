package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.AuthInstitution;

public interface AuthInstitutionServicesI {

    /**
     * 查询机构
     *
     * @param auth_type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<AuthInstitution> queryAuthInstitutionList(int auth_type, int pageNum, int pageSize, String institutionName);

    /**
     * 创建机构
     *
     * @param institution
     * @return
     */

    Result<AuthInstitution> createAuthInstitution(AuthInstitution institution);

    /**
     * 更新机构
     *
     * @param id
     * @param authInstitution
     * @return
     */
    Result<AuthInstitution> updateAuthInstitution(int id, AuthInstitution authInstitution);

    /**
     * 删除机构
     *
     * @param id
     * @return
     */
    Result<AuthInstitution> deleteAuthInstitution(int id);


    boolean checkAuthInstitutionExist(AuthInstitution authInstitution);
}
