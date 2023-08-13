package com.zlht.pose.management.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.WeChatServicesI;
import com.zlht.pose.management.api.utils.PageInfo;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.entity.WeChat;
import com.zlht.pose.management.dao.mapper.WeChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServicesImpl extends BaseServiceImpl implements WeChatServicesI {
    @Autowired
    WeChatMapper weChatMapper;

    @Override
    public Result<PageInfo<WeChat>> queryWeChatList(User loginUser, int currentPage, int pageSize, int status, String keyword) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page page = new Page<>(currentPage, pageSize);
        Page<Map<String, Object>> weChatPage = weChatMapper.selectWechat(page, keyword, status);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(weChatPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createWeChat(User loginUser, WeChat weChat) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", weChat.getName());
        if (weChatMapper.exists(checkWrapper)) {
            putMsg(map, 400, "小程序信息已存在！");
            return map;
        }
        int resNum = weChatMapper.insert(weChat);
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "创建小程序信息成功！");
        } else {
            putMsg(map, 400, "创建小程序信息失败！");
        }
        return map;

    }


    @Override
    public Map<String, Object> updateWeChat(User loginUser, int id, WeChat weChat) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }

        if (!checkWeChatExistById(id)) {
            putMsg(map, 400, "所更新的小程序信息ID不存在!");
            return map;
        }
        QueryWrapper checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("name", weChat.getName());
        checkWrapper.ne("id", id);
        if (weChatMapper.exists(checkWrapper)) {
            putMsg(map, 400, "所更换小程序信息已存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = weChatMapper.update(weChat, queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新小程序信息成功！");
        } else {
            putMsg(map, 400, "更新小程序信息失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteWeChat(User loginUser, int id) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (!checkWeChatExistById(id)) {
            putMsg(map, 400, "所删除的小程序信息ID不存在!");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = weChatMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除小程序信息成功！");
        } else {
            putMsg(map, 400, "删除小程序信息失败！");
        }
        return map;
    }

    @Override
    public boolean checkSportExistByName(WeChat weChat) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", weChat.getName());
        return weChatMapper.exists(queryWrapper);
    }


    @Override
    public boolean checkWeChatExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return weChatMapper.exists(queryWrapper);
    }
}
