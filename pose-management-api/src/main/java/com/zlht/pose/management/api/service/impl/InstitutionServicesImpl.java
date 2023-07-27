package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InstitutionServicesImpl extends BaseServiceImpl<Institution> implements InstitutionServicesI {

    private static final Logger logger = LogManager.getLogger(InstitutionServicesImpl.class);

    @Autowired
    InstitutionMapper institutionMapper;

    @Override
    public Result queryInstitutionList(int type, int pageNum, int pageSize, String keyword) {

        Result result = new Result();
        Page<Institution> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Institution> wapper = new QueryWrapper<Institution>();
        if (type != -1) wapper.eq("type", type);
        if (keyword != null) wapper.and(nc -> nc.like("name", keyword));
        Page<Institution> institutionPage = institutionMapper.selectPage(page, wapper);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(institutionPage.getRecords());
        return result;
    }
    @Override
    public Result queryInstitutionMap() {

        Result result = new Result();
        List<Map<String,Object>> list = institutionMapper.queryInstitutionMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }

    @Override
    public Map<String, Object> createInstitution(Institution institution) {
        Map<String, Object> map = new HashMap<>();
        //exist?
        if (checkInstitutionExistByName(institution)) {
            putMsg(map, 400, "机构名已存在!");
            return map;
        }
        if (!validateInstitutionName(institution)) {
            putMsg(map, 400, "机构名或昵称不符合规范！");
            return map;
        }
        if (!ValidateService.validateEmail(institution.getEmail())) {
            putMsg(map, 400, "邮箱格式不满足！");
            return map;
        }

        int resNum = institutionMapper.insert(institution);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "创建机构成功！");
        } else {
            putMsg(map, 400, "创建机构失败！");
        }
        return map;

    }


    @Override
    public Map<String, Object> updateInstitution(int id, Institution institution) {

        Map<String, Object> map = new HashMap<>();
        if (!validateInstitutionName(institution)) {
            putMsg(map, 400, "机构名或昵称不符合规范！");
            return map;
        }

        if (!checkInstitutionExistById(id)) {
            putMsg(map, 400, "所更新的机构ID不存在!");
            return map;
        }
        //exist?
        if (checkInstitutionExistByName(institution)) {
            putMsg(map, 400, "机构名已存在!");
            return map;
        }

        if (!ValidateService.validateEmail(institution.getEmail())) {
            putMsg(map, 400, "邮箱格式不满足！");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = institutionMapper.update(institution, queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新机构成功！");
        } else {
            putMsg(map, 400, "更新机构失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteInstitution(int id) {

        Map<String, Object> map = new HashMap<>();
        if (!checkInstitutionExistById(id)) {
            putMsg(map, 400, "所删除的机构ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = institutionMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除机构成功！");
        } else {
            putMsg(map, 400, "删除机构失败！");
        }
        return map;
    }


    @Override
    public boolean checkInstitutionExistByName(Institution institution) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", institution.getName());
        return institutionMapper.exists(queryWrapper);
    }


    @Override
    public boolean checkInstitutionExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return institutionMapper.exists(queryWrapper);
    }


    private boolean validateInstitutionName(Institution institution) {
        if (institution == null) {
            return false;
        }

        String name = institution.getName();
        if (StringUtils.isBlank(name) || StringUtils.containsWhitespace(name)) {
            return false;
        }
        return true;
    }
}
