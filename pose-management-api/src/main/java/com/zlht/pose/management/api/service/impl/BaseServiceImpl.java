package com.zlht.pose.management.api.service.impl;


import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.service.BaseServiceI;
import com.zlht.pose.management.dao.entity.User;

import java.util.Map;

public class BaseServiceImpl<T> implements BaseServiceI<T> {

    public void putMsg(Map<String, Object> result, int code, String msg) {
        result.put("code", code);
        result.put("msg", msg);
        if (code == Status.SUCCESS.getCode()) {
            result.put("status", Status.SUCCESS);
        }
    }

    @Override
    public boolean canOperator(User operateUser) {
        return operateUser != null && operateUser.getType() == 0;
    }
}
