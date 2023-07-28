package com.zlht.pose.management.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.TemplateServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Template;
import com.zlht.pose.management.dao.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TemplateServicesImpl extends BaseServiceImpl implements TemplateServicesI {
    @Autowired
    TemplateMapper templateMapper;

    @Override
    public Result queryTemplateList(int pageNum, int pageSize, int status, String keyword) {
        Result result = new Result();
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (keyword != null) queryWrapper.eq("name", keyword);
        Page<Template> templatePage = templateMapper.selectPage(page, queryWrapper);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(templatePage.getRecords());
        return result;
    }

    @Override
    public Map<String, Object> createTemplate(Template template) {

        Map<String, Object> map = new HashMap<>();
        if (checkSportExistByName(template)) {
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
    public Map<String, Object> updateTemplate(int id, Template template) {

        Map<String, Object> map = new HashMap<>();
        if (!checkTemplateExistById(id)) {
            putMsg(map, 400, "所更新的模板信息ID不存在!");
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
    public Map<String, Object> deleteTemplate(int id) {

        Map<String, Object> map = new HashMap<>();
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
    public boolean checkSportExistByName(Template template) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", template.getName());
        return templateMapper.exists(queryWrapper);
    }


    @Override
    public boolean checkTemplateExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return templateMapper.exists(queryWrapper);
    }
}
