package com.zlht.pbr.algorithm.management.api.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.TemplateServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.Template;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.TemplateMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TemplateServicesImpl extends BaseServiceImpl implements TemplateServicesI {
    @Autowired
    TemplateMapper templateMapper;

    @Override
    public Result<PageInfo<Template>> queryTemplateList(User loginUser, int currentPage, int pageSize, int status, String keyword) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (keyword != null) queryWrapper.eq("name", keyword);
        Page<Template> templatePage = templateMapper.selectPage(page, queryWrapper);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(templatePage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Result<Template> queryTemplateMap(User loginUser) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = templateMapper.queryTemplateMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }

    @Override
    public Map<String, Object> createTemplate(User loginUser, Template template) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", template.getName());
        if (templateMapper.exists(checkWrapper)) {
            putMsg(map, 400, "模板信息已存在！");
            return map;
        }
        int resNum = templateMapper.insert(template);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "创建模板信息成功！");
        } else {
            putMsg(map, 400, "创建模板信息失败！");
        }
        return map;

    }


    @Override
    public Map<String, Object> updateTemplate(User loginUser, int id, Template template) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkTemplateExistById(id)) {
            putMsg(map, 400, "所更新的模板信息ID不存在!");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", template.getName());
        checkWrapper.ne("id", id);
        if (templateMapper.exists(checkWrapper)) {
            putMsg(map, 400, "模板信息已存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = templateMapper.update(template, queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新模板信息成功！");
        } else {
            putMsg(map, 400, "更新模板信息失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteTemplate(User loginUser, int id) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkTemplateExistById(id)) {
            putMsg(map, 400, "所删除的模板信息ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = templateMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除模板信息成功！");
        } else {
            putMsg(map, 400, "删除模板信息失败！");
        }
        return map;
    }


    @Override
    public boolean checkTemplateExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return templateMapper.exists(queryWrapper);
    }
}
