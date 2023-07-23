package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.zlht.pose.management.api.service.UserServicesI;
import com.zlht.pose.management.api.utils.Result;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.UserMapper;


import com.zlht.pose.management.tools.service.Argon2PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl extends BaseServiceImpl<User> implements UserServicesI {

    private static final Logger logger = LogManager.getLogger(UserServicesImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public Result<User> queryUserList(int type, int pageNum, int pageSize) {

        List<User> userList = new ArrayList<>();
        Page<User> page = new Page<>(pageNum, pageSize);

        QueryWrapper<User> wapper = new QueryWrapper<User>();
        wapper.eq("type", type);
        Page<User> userPage = userMapper.selectPage(page, wapper);
        if (userPage != null) {
            for (User user : userPage.getRecords()) {
                userList.add(user);
            }
            return success(userList);
        } else {
            return faild(400, "未查询到用户！");
        }

    }

    @Override
    public Result<User> createUser(User user) {

        //exist?
        if (checkUserExist(user)) {
            return faild(400, "用户名重复！");
        }
        //管理员的话加密 密码

        int resNum = userMapper.insert(encipher(user));
        if (resNum >= 1) {
            return success(null);
        } else {
            return faild(400, "插入用户失败");
        }

    }


    @Override
    public Result<User> updateUser(int id, User user) {
        //exist?
        if (checkUserExist(user)) {
            return faild(400, "用户名重复！");
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = userMapper.update(encipher(user), queryWrapper);
        if (update >= 1) {
            return success(null);
        } else {
            return faild(400, "更新用户失败");
        }
    }

    /**
     * 检查用户是否重复
     * @param user
     * @return
     */
    @Override
    public boolean checkUserExist(User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        return userMapper.exists(queryWrapper);
    }

    /**
     * 加密
     * @param user
     * @return
     */
    private User encipher(User user) {
        String passwordFromArgon2 = Argon2PasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordFromArgon2);
        return user;
    }
}
