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
    Result<User> queryUserList(int type, int pageNum, int pageSize, String nickname);

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

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Result<User> deleteUser(int id);


    /**
     * 认证用户
     *
     * @param username
     * @param password
     * @return
     */
    Result<User> authorizedUser(String username, String password);

    boolean checkUserExist(User user);
}
