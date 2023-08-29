package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.AuthInstitutionAlgServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.AuthInstitutionAlg;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.AuthInstitutionAlgMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthInstitutionAlgServicesImpl extends BaseServiceImpl<AuthInstitutionAlg> implements AuthInstitutionAlgServicesI {

    private static final Logger logger = LogManager.getLogger(AuthInstitutionAlgServicesImpl.class);

    @Autowired
    AuthInstitutionAlgMapper authInstitutionAlgMapper;

    @Override
    public Result<PageInfo> queryAuthInstitutionAlgList(User loginUser, int auth_type, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> institutionPage = authInstitutionAlgMapper.selectAuthInstitutionsWithNickname(page, keyword, auth_type);
        logger.info("queryAuthInstitutionAlgList() method. username={}, auth_type={}, currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), auth_type, currentPage, pageSize, keyword);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(institutionPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createAuthInstitution(User loginUser, AuthInstitutionAlg authInstitutionAlg) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        //check Duplication
        if (checkAuthDuplication(authInstitutionAlg)) {
            putMsg(map, 400, "机构授权已存在!");
            return map;
        }
        authInstitutionAlg.setAuthAdmin(loginUser.getId());
        try {
            authInstitutionAlgMapper.insert(authInstitutionAlg);
            putMsg(map, Status.SUCCESS.getCode(), "新建机构授权成功！");
        } catch (Exception e) {
            String errMsg = "新建机构授权失败";
            logger.error("deleteAlgorithm() method .message={}, authInstitutionAlg={}", errMsg, authInstitutionAlg, e);
            putMsg(map, 400, errMsg);
        }
        return map;

    }


    @Override
    public Map<String, Object> updateAuthInstitution(User loginUser, int id, AuthInstitutionAlg authInstitutionAlg) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }

        //check Exist
        if (!checkAuthExistById(id)) {
            String errorMsg = "所更新机构不存在";
            logger.error("updateAuthInstitution() method .message={}, id={},authInstitutionAlg={}", errorMsg, id, authInstitutionAlg);
            putMsg(map, 400, errorMsg);
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("institution_id", authInstitutionAlg.getInstitutionId());
        checkWrapper.eq("auth_alg_id", authInstitutionAlg.getInstitutionId());
        checkWrapper.ne("id", id);
        //exist?
        if (authInstitutionAlgMapper.exists(checkWrapper)) {
            putMsg(map, 400, "机构授权已存在!");
            return map;
        }

        authInstitutionAlg.setId(id);
        authInstitutionAlg.setAuthAdmin(loginUser.getId());
        try {
            authInstitutionAlgMapper.updateById(authInstitutionAlg);
            putMsg(map, Status.SUCCESS.getCode(), "更新机构授权成功！");
        } catch (Exception e) {
            String errMsg = "更新授权失败";
            logger.error("updateAuthInstitution() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAuthInstitution(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkAuthExistById(id)) {
            String errorMsg = "删除的机构不存在";
            logger.error("deleteAuthInstitution() method .message={}, id={}", errorMsg, id);
            putMsg(map, 400, errorMsg);
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            authInstitutionAlgMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除机构授权成功！");
        } catch (Exception e) {
            String errMsg = "删除机构失败";
            logger.error("deleteAuthInstitution() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;

    }


    /**
     * 检查授权是否重复
     *
     * @param authInstitutionAlg
     * @return
     */
    @Override
    public boolean checkAuthDuplication(AuthInstitutionAlg authInstitutionAlg) {
        QueryWrapper queryWrapper = new QueryWrapper<AuthInstitutionAlg>();
        queryWrapper.eq("institution_id", authInstitutionAlg.getInstitutionId());
        queryWrapper.eq("auth_alg_id", authInstitutionAlg.getAuthAlgId());
        return authInstitutionAlgMapper.exists(queryWrapper);
    }

    /**
     * 检查授权是否存在
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkAuthExistById(int id) {
        return authInstitutionAlgMapper.selectById(id) != null;
    }


}
