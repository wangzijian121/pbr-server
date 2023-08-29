package com.zlht.pbr.algorithm.management.base.impl;


import com.zlht.pbr.algorithm.management.base.BaseServiceI;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.enums.Status;

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

    @Override
    public boolean canCommit(User operateUser) {
        return operateUser != null && operateUser.getType() == 1;
    }
}
