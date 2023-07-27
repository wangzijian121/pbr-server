package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.Sport;
import com.zlht.pose.management.dao.entity.User;

import java.util.Map;

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

    Map<String, Object> createUser(User user);

    /**
     * 更新用户
     *
     * @param id
     * @param user
     * @return
     */
    Map<String, Object> updateUser(int id, User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Map<String, Object> deleteUser(int id);


    /**
     * 认证用户
     *
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> authorizedUser(String username, String password);

    /**
     * 检查用户是否已存在
     *
     * @param user
     * @return
     */
    boolean checkUserExistByName(User user);

    /**
     * 通过ID判断是否存在（删除更新判断）
     *
     * @param user
     * @return
     */
    boolean checkUserExistById(int id);
}
