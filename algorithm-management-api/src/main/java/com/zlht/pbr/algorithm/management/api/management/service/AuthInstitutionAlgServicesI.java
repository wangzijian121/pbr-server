package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.AuthInstitutionAlg;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

public interface AuthInstitutionAlgServicesI {

    /**
     * 查询机构
     *
     * @param auth_type
     * @param currentPage
     * @param pageSize
     * @return
     */
    Result<PageInfo> queryAuthInstitutionAlgList(User loginUser, int auth_type, int currentPage, int pageSize, String keyword);

    /**
     * 创建机构
     *
     * @param institution
     * @return
     */

    Map<String, Object> createAuthInstitution(User loginUser, AuthInstitutionAlg institution);

    /**
     * 更新机构
     *
     * @param id
     * @param authInstitutionAlg
     * @return
     */
    Map<String, Object> updateAuthInstitution(User loginUser, int id, AuthInstitutionAlg authInstitutionAlg);

    /**
     * 删除机构
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteAuthInstitution(User loginUser, int id);

    boolean checkAuthDuplication(AuthInstitutionAlg authInstitutionAlg);

    boolean checkAuthExistById(int id);

}
