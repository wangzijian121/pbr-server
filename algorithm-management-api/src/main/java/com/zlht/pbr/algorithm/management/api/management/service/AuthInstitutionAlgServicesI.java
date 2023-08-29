package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.AuthInstitutionAlg;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface AuthInstitutionAlgServicesI {

    /**
     * 查询机构
     *
     * @param loginUser
     * @param authType
     * @param currentPage
     * @param pageSize
     * @param keyword
     * @return
     */
    Result<PageInfo> queryAuthInstitutionAlgList(User loginUser, int authType, int currentPage, int pageSize, String keyword);

    /**
     * 创建机构
     *
     * @param loginUser
     * @param institution
     * @return
     */

    Map<String, Object> createAuthInstitution(User loginUser, AuthInstitutionAlg institution);

    /**
     * 更新机构
     *
     * @param loginUser
     * @param id
     * @param authInstitutionAlg
     * @return
     */
    Map<String, Object> updateAuthInstitution(User loginUser, int id, AuthInstitutionAlg authInstitutionAlg);

    /**
     * 删除机构
     *
     * @param loginUser
     * @param id
     * @return
     */
    Map<String, Object> deleteAuthInstitution(User loginUser, int id);

    /**
     * 检查验证复制
     *
     * @param authInstitutionAlg
     * @return
     */
    boolean checkAuthDuplication(AuthInstitutionAlg authInstitutionAlg);

    /**
     * 检查认证存在Id
     *
     * @param id
     * @return
     */
    boolean checkAuthExistById(int id);

}
