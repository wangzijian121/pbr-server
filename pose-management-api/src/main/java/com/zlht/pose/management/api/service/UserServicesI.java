package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.User;

public interface UserServicesI {

    /**
     * 查询用户
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<User> queryUserList(int type, int pageNum, int pageSize);

    /**
     * 创建用户
     *
     * @param user
     * @return
     */

    Result<User> createUser(User user);

    /**
     * 更新用户
     *
     * @param id
     * @param user
     * @return
     */
    Result<User> updateUser(int id, User user);

    boolean checkUserExist(User user);
}
