package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.AuthInstitutionAlgServicesI;
import com.zlht.pose.management.api.utils.PageInfo;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.AuthInstitutionAlg;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.AuthInstitutionAlgMapper;
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
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(institutionPage.getRecords());
        result.setData(pageInfo);
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
        int resNum = authInstitutionAlgMapper.insert(authInstitutionAlg);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "创建授权成功！");
        } else {
            putMsg(map, 400, "新建机构授权失败！");
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
            logger.error("所更新机构不存在！");
            putMsg(map, 400, "所更新机构不存在！");
            return map;
        }

        //check Duplication
        if (checkAuthDuplication(authInstitutionAlg)) {
            putMsg(map, 400, "机构授权已存在!");
            return map;
        }
        authInstitutionAlg.setId(id);
        int update = authInstitutionAlgMapper.updateById(authInstitutionAlg);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新授权成功！");
        } else {
            putMsg(map, 400, "更新授权失败！");
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
            logger.error("所删除机构不存在！");
            putMsg(map, 400, "所删除机构不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = authInstitutionAlgMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除授权成功！");
        } else {
            putMsg(map, 400, "删除机构失败！");
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
        queryWrapper.eq("institution_id", authInstitutionAlg.getInstitution_id());
        queryWrapper.eq("auth_alg_id", authInstitutionAlg.getAuth_alg_id());
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
