package com.zlht.pbr.algorithm.management.api.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.management.api.wechat.service.WxWeChatServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.UserMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
@Service
public class WxWeChatServiceImpl extends BaseServiceImpl implements WxWeChatServiceI {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> adminOrNot(String openId) {

        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",2);
        queryWrapper.eq("attr->'$.openId'", openId);
        boolean check = userMapper.exists(queryWrapper);
        Map<String, Object> mapCheck = new HashMap<>(1);
        mapCheck.put("adminOrNot", check);
        map.put("data", mapCheck);
        putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        return map;
    }
}
