package com.zlht.pose.management.api.service;

import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.User;

public interface UserServicesI {
    //按类型查询
    Result<User> queryUserList(int type, int pageNum, int pageSize);

    //创建用户
    Result<User> createUser(User user);


    boolean checkUserExist(User user);
}
