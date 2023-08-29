package com.zlht.pbr.algorithm.management.base;


import com.zlht.pbr.algorithm.management.dao.entity.User;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface BaseServiceI<T> {


    /**
     * 防止信息
     *
     * @param result
     * @param code
     * @param msg
     */
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
