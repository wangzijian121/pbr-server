package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.service.SportServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Sport;
import com.zlht.pose.management.dao.mapper.SportMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportServicesImpl extends BaseServiceImpl<Sport> implements SportServicesI {

    private static final Logger logger = LogManager.getLogger(SportServicesImpl.class);

    @Autowired
    SportMapper sportMapper;

    @Override
    public Result<Sport> querySportList(int type, int pageNum, int pageSize, String name) {
        
        List<Sport> sportList = new ArrayList<>();
        Page<Sport> page = new Page<>(pageNum, pageSize);

        QueryWrapper<Sport> wapper = new QueryWrapper<Sport>();
        if (type != -1) wapper.eq("type", type);
        if (name != null) wapper.and(nc -> nc.like("name", name));
        Page<Sport> sportPage = sportMapper.selectPage(page, wapper);
        if (sportPage != null) {
            for (Sport sport : sportPage.getRecords()) {
                sportList.add(sport);
            }
            return success(sportList);
        } else {
            return faild(400, "未查询到体育！");
        }

    }

    @Override
    public Result<Sport> createSport(Sport sport) {
        //exist?
        if (checkSportExist(sport)) {
            return faild(400, "体育名重复！");
        }

        if (!validateSportName(sport)) {
            return faild(400, "体育名或昵称不符合规范！");
        }


        int resNum = sportMapper.insert(sport);
        if (resNum >= 1) {
            return success(null);
        } else {
            return faild(400, "插入体育失败");
        }

    }


    @Override
    public Result<Sport> updateSport(int id, Sport sport) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = sportMapper.update(sport, queryWrapper);
        if (update >= 1) {
            return success(null);
        } else {
            return faild(400, "更新体育失败");
        }
    }

    @Override
    public Result<Sport> deleteSport(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = sportMapper.delete(queryWrapper);
        if (delete >= 1) {
            return success(null);
        } else {
            return faild(400, "删除体育失败！");
        }

    }


    /**
     * 检查体育是否重复
     *
     * @param sport
     * @return
     */
    @Override
    public boolean checkSportExist(Sport sport) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sport_name", sport.getName());
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
