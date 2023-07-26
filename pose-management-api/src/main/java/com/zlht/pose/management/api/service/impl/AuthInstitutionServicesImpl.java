package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.AuthInstitutionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.AuthInstitution;

import com.zlht.pose.management.dao.mapper.AuthInstitutionMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AuthInstitutionServicesImpl extends BaseServiceImpl<AuthInstitution> implements AuthInstitutionServicesI {

    private static final Logger logger = LogManager.getLogger(AuthInstitutionServicesImpl.class);

    @Autowired
    AuthInstitutionMapper authInstitutionMapper;

    @Override
    public Result queryAuthInstitutionList(int auth_type, int pageNum, int pageSize, String institutionName) {

        Result result = new Result();
        List<AuthInstitution> authInstitutionList = new ArrayList<>();
        Page page = new Page<>(pageNum, pageSize);
        System.out.println(auth_type);
        System.out.println(institutionName);
        Page<Map<String, Object>> institutionPage = authInstitutionMapper.selectAuthInstitutionsWithNickname(page, institutionName, auth_type);
        System.out.println(institutionPage);
        System.out.println(institutionPage.getRecords());
        result.setData(institutionPage.getRecords());
        return result;
    }

    @Override
    public Result<AuthInstitution> createAuthInstitution(AuthInstitution authInstitution) {
        //exist?
        if (checkAuthInstitutionExist(authInstitution)) {
            return faild(400, "机构名重复！");
        }

        if (!validateAuthInstitutionName(authInstitution)) {
            return faild(400, "机构名或昵称不符合规范！");
        }


        int resNum = authInstitutionMapper.insert(authInstitution);
        if (resNum >= 1) {
            return success(null);
        } else {
            return faild(400, "插入机构失败");
        }

    }


    @Override
    public Result<AuthInstitution> updateAuthInstitution(int id, AuthInstitution authInstitution) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = authInstitutionMapper.update(authInstitution, queryWrapper);
        if (update >= 1) {
            return success(null);
        } else {
            return faild(400, "更新机构失败");
        }
    }

    @Override
    public Result<AuthInstitution> deleteAuthInstitution(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = authInstitutionMapper.delete(queryWrapper);
        if (delete >= 1) {
            return success(null);
        } else {
            return faild(400, "删除机构失败！");
        }

    }


    /**
     * 检查机构是否重复
     *
     * @param authInstitution
     * @return
     */
    @Override
    public boolean checkAuthInstitutionExist(AuthInstitution authInstitution) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("institution_name", authInstitution.getInstitutionName());
        return authInstitutionMapper.exists(queryWrapper);
    }


    private static boolean validateAuthInstitutionName(AuthInstitution authInstitution) {
        if (authInstitution == null) {
            return false;
        }

        String name = authInstitution.getInstitutionName();
        // 校验 institution_name不为空，并且没有空格
        if (StringUtils.isBlank(name) || StringUtils.containsWhitespace(name)) {
            return false;
        }

        return true;
    }


}
