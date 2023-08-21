package com.zlht.pbr.algorithm.management.base;


import com.zlht.pbr.algorithm.management.dao.entity.User;

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

    /**
     * 检查权限
     *
     * @param operateUser
     * @return
     */
    boolean canCommit(User operateUser);

}
