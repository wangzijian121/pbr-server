package com.zlht.pbr.algorithm.management.api.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.WeChatServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.entity.WeChat;
import com.zlht.pbr.algorithm.management.dao.mapper.WeChatMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServicesImpl extends BaseServiceImpl implements WeChatServicesI {

    private static final Logger logger = LogManager.getLogger(UserServicesImpl.class);

    @Autowired
    private WeChatMapper weChatMapper;

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
        logger.info("queryWeChatList() method. username={},type={}, currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), currentPage, pageSize, keyword);
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
        try {
            weChatMapper.insert(weChat);
            putMsg(map, Status.SUCCESS.getCode(), "创建小程序信息成功！");
        } catch (Exception e) {
            String errMsg = "创建小程序信息失败";
            logger.error("createWeChat() method .message={}, weChat={}", errMsg, weChat, e);
            putMsg(map, 400, errMsg);
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
        try {
            weChatMapper.update(weChat, queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新小程序信息成功！");
        } catch (Exception e) {
            String errMsg = "更新小程序信息失败";
            logger.error("updateWeChat() method .message={}, weChat={}", errMsg, weChat, e);
            putMsg(map, 400, errMsg);

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

        try {
            weChatMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除小程序信息成功！");
        } catch (Exception e) {
            String errMsg = "删除小程序信息失败";
            logger.error("deleteWeChat() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
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
