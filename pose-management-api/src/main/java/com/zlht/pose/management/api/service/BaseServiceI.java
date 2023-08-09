package com.zlht.pose.management.api.service;


import com.zlht.pose.management.dao.entity.User;

import java.util.Map;

public interface BaseServiceI<T> {


    void putMsg(Map<String, Object> result, int code, String msg);

    /**
     * 检查权限
     *
     * @param operateUser
     * @return
     */
    boolean canOperator(User operateUser);

}
