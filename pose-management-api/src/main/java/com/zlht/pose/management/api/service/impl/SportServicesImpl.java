package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.SportServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Sport;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.SportMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SportServicesImpl extends BaseServiceImpl<Sport> implements SportServicesI {

    private static final Logger logger = LogManager.getLogger(SportServicesImpl.class);

    @Autowired
    SportMapper sportMapper;

    @Override
    public Result<Sport> querySportList(User loginUser, int type, int pageNum, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<Sport> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Sport> wapper = new QueryWrapper<Sport>();
        if (type != -1) wapper.eq("type", type);
        if (keyword != null) wapper.and(nc -> nc.like("name", keyword));
        Page<Sport> sportPage = sportMapper.selectPage(page, wapper);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(sportPage.getRecords());
        return result;
    }

    @Override
    public Result<Sport> querySportMap(User loginUser) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = sportMapper.querySportMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }

    @Override
    public Map<String, Object> createSport(User loginUser, Sport sport) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!validateSportName(sport)) {
            putMsg(map, 400, "体育名或昵称不符合规范！");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", sport.getName());
        checkWrapper.eq("type", sport.getType());
        //exist?
        if (sportMapper.exists(checkWrapper)) {
            putMsg(map, 400, "该体育类型下已存在该体育！");
            return map;
        }

        int resNum = sportMapper.insert(sport);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "新建体育成功！");
        } else {
            putMsg(map, 400, "新建体育失败！");
        }
        return map;
    }


    @Override
    public Map<String, Object> updateSport(User loginUser, int id, Sport sport) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkSportExistById(id)) {
            putMsg(map, 400, "更新的体育ID不存在！");
            return map;
        }

        if (!validateSportName(sport)) {
            putMsg(map, 400, "体育名不符合规范！");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", sport.getName());
        checkWrapper.eq("type", sport.getType());
        checkWrapper.ne("id", id);
        //exist?
        if (sportMapper.exists(checkWrapper)) {
            putMsg(map, 400, "该体育类型下已存在该体育！");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = sportMapper.update(sport, queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新体育成功！");
        } else {
            putMsg(map, 400, "更新体育失败！");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteSport(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkSportExistById(id)) {
            putMsg(map, 400, "删除的体育ID不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = sportMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除体育成功！");
        } else {
            putMsg(map, 400, "删除体育失败！");
        }
        return map;
    }

    @Override
    public boolean checkSportExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return sportMapper.exists(queryWrapper);
    }


    private static boolean validateSportName(Sport sport) {
        if (sport == null) {
            return false;
        }
        String name = sport.getName();
        // 校验 sport_name不为空，并且没有空格
        if (StringUtils.isBlank(name) || StringUtils.containsWhitespace(name)) {
            return false;
        }
        return true;
    }

}
