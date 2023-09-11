package com.zlht.pbr.algorithm.management.api.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pbr.algorithm.management.api.wechat.service.WxWeChatServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.WeChat;
import com.zlht.pbr.algorithm.management.dao.mapper.WeChatMapper;
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
    private WeChatMapper weChatMapper;

    @Override
    public Map<String, Object> adminOrNot(String linkCode, String openId) {

        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<WeChat> queryWrapper = new QueryWrapper();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.like("admin_openid", openId);
        boolean check = weChatMapper.exists(queryWrapper);
        Map<String, Object> mapCheck = new HashMap<>(1);
        mapCheck.put("adminOrNot", check);
        map.put("data", mapCheck);
        putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        return map;
    }
}
