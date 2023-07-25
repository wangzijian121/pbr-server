package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.service.InstitutionServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Institution;
import com.zlht.pose.management.dao.mapper.InstitutionMapper;
import com.zlht.pose.management.tools.service.ValidateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionServicesImpl extends BaseServiceImpl<Institution> implements InstitutionServicesI {

    private static final Logger logger = LogManager.getLogger(InstitutionServicesImpl.class);

    @Autowired
    InstitutionMapper institutionMapper;

    @Override
    public Result<Institution> queryInstitutionList(int type, int pageNum, int pageSize, String name) {


        List<Institution> institutionList = new ArrayList<>();
        Page<Institution> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Institution> wapper = new QueryWrapper<Institution>();
        if (type != -1) wapper.eq("type", type);
        if (name != null) wapper.and(nc -> nc.like("name", name));
        Page<Institution> institutionPage = institutionMapper.selectPage(page, wapper);
        if (institutionPage != null) {
            for (Institution institution : institutionPage.getRecords()) {
                institutionList.add(institution);
            }
            return success(institutionList);
        } else {
            return faild(400, "未查询到机构！");
        }

    }

    @Override
    public Result<Institution> createInstitution(Institution institution) {
        //exist?
        if (checkInstitutionExist(institution)) {
            return faild(400, "机构名重复！");
        }

        if (!validateInstitutionName(institution)) {
            return faild(400, "机构名或昵称不符合规范！");
        }
        if (!ValidateService.validateEmail(institution.getEmail())) {
            return faild(400, "邮箱格式不满足！");
        }

        int resNum = institutionMapper.insert(institution);
        if (resNum >= 1) {
            return success(null);
        } else {
            return faild(400, "插入机构失败");
        }

    }


    @Override
    public Result<Institution> updateInstitution(int id, Institution institution) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = institutionMapper.update(institution, queryWrapper);
        if (update >= 1) {
            return success(null);
        } else {
            return faild(400, "更新机构失败");
        }
    }

    @Override
    public Result<Institution> deleteInstitution(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = institutionMapper.delete(queryWrapper);
        if (delete >= 1) {
            return success(null);
        } else {
            return faild(400, "删除机构失败！");
        }

    }


    /**
     * 检查机构是否重复
     *
     * @param institution
     * @return
     */
    @Override
    public boolean checkInstitutionExist(Institution institution) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", institution.getName());
        return institutionMapper.exists(queryWrapper);
    }


    private static boolean validateInstitutionName(Institution institution) {
        if (institution == null) {
            return false;
        }

        String name = institution.getName();
        // 校验 Institutionname 和 nickname 不为空，并且没有空格
        if (StringUtils.isBlank(name) || StringUtils.containsWhitespace(name)) {
            return false;
        }

        return true;
    }
}
