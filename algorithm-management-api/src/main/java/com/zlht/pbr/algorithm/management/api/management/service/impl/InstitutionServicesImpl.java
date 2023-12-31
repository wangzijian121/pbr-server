package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.InstitutionServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Institution;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.InstitutionMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.tools.service.ValidateService;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class InstitutionServicesImpl extends BaseServiceImpl<Institution> implements InstitutionServicesI {

    private static final Logger logger = LogManager.getLogger(InstitutionServicesImpl.class);

    @Autowired
    private InstitutionMapper institutionMapper;

    @Override
    public Result<PageInfo<Institution>> queryInstitutionList(User loginUser, int type, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Institution> page = new Page<>(currentPage, pageSize);

        QueryWrapper<Institution> wapper = new QueryWrapper<Institution>();
        if (type != -1) {
            wapper.eq("type", type);
        }
        if (keyword != null) {
            wapper.and(nc -> nc.like("name", keyword));
        }
        Page<Institution> institutionPage = institutionMapper.selectPage(page, wapper);
        logger.info("queryInstitutionList() method. username={},type={}, currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), type, currentPage, pageSize, keyword);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(institutionPage.getRecords());
        result.setCode(200);
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(pageInfo);
        return result;
    }

    @Override
    public Result queryInstitutionMap(User loginUser) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = institutionMapper.queryInstitutionMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }

    @Override
    public Map<String, Object> createInstitution(User loginUser, Institution institution) {
        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", institution.getName());
        //exist?
        if (institutionMapper.exists(queryWrapper)) {
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
        try {
            institutionMapper.insert(institution);
            putMsg(map, Status.SUCCESS.getCode(), "创建机构成功！");
        } catch (Exception e) {
            String errMsg = "创建机构失败";
            logger.error("createInstitution() method .message={}, institution={}", errMsg, institution, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }


    @Override
    public Map<String, Object> updateInstitution(User loginUser, int id, Institution institution) {

        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!validateInstitutionName(institution)) {
            putMsg(map, 400, "机构名或昵称不符合规范！");
            return map;
        }

        if (!checkInstitutionExistById(id)) {
            putMsg(map, 400, "所更新的机构ID不存在!");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", institution.getName());
        checkWrapper.ne("id", id);
        //exist?
        if (institutionMapper.exists(checkWrapper)) {
            putMsg(map, 400, "机构名已存在!");
            return map;
        }

        if (!ValidateService.validateEmail(institution.getEmail())) {
            putMsg(map, 400, "邮箱格式不满足！");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            putMsg(map, Status.SUCCESS.getCode(), "更新机构成功！");
            institutionMapper.update(institution, queryWrapper);
        } catch (Exception e) {
            String errMsg = "更新机构失败";
            logger.error("updateInstitution() method .message={}, institution={}", errMsg, institution, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteInstitution(User loginUser, int id) {

        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkInstitutionExistById(id)) {
            putMsg(map, 400, "所删除的机构ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            institutionMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除机构成功！");
        } catch (Exception e) {
            String errMsg = "删除机构失败";
            logger.error("deleteInstitution() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
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
