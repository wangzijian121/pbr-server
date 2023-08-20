package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pose.enums.Status;
import com.zlht.pose.management.api.service.SessionServiceI;
import com.zlht.pose.management.api.service.UserServicesI;
import com.zlht.pose.utils.PageInfo;
import com.zlht.pose.utils.Result;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.UserMapper;
import com.zlht.pose.management.tools.service.Argon2PasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServicesImpl extends BaseServiceImpl<User> implements UserServicesI {

    private static final Logger logger = LogManager.getLogger(UserServicesImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    SessionServiceI sessionServiceI;


    @Override
    public Result<PageInfo<User>> queryUserList(User loginUser, int type, int currentPage, int pageSize, String keyword) {

        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        Page<User> page = new Page<>(currentPage, pageSize);

        QueryWrapper<User> wapper = new QueryWrapper<User>();
        if (type != -1) wapper.eq("type", type);
        if (keyword != null) {
            wapper.like("nickname", keyword);
        }
        Page<User> userPage = userMapper.selectPage(page, wapper);
        PageInfo pageInfo = new PageInfo(currentPage, pageSize);
        pageInfo.setTotal((int) page.getTotal());
        pageInfo.setTotalList(userPage.getRecords());
        result.setData(pageInfo);
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        return result;
    }

    @Override
    public Map<String, Object> createUser(User loginUser, User user) {

        Map<String, Object> map = new HashMap<>();
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (checkUserExistByUserName(user)) {
            putMsg(map, 400, "用户已存在！");
            return map;
        }
        if (!validateUserName(user)) {
            putMsg(map, 400, "用户名或昵称不符合规范！");
            return map;
        }
        //管理员的话加密 密码

        int resNum = userMapper.insert(encipher(user));
        if (resNum >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "新建用户成功！");
        } else {
            putMsg(map, 400, "新建用户失败！");
        }
        return map;
    }


    @Override
    public Map<String, Object> updateUser(User loginUser, int id, User user) {

        Map<String, Object> map = new HashMap<>();
        if (!checkUserExistById(id)) {
            putMsg(map, 400, "所更新用户不存在！");
            return map;
        }

        if (checkUserExistByIdName(id, user)) {
            putMsg(map, 400, "用户已存在！");
            return map;
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int update = userMapper.update(encipher(user), queryWrapper);
        if (update >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "更新用户成功！");
        } else {
            putMsg(map, 400, "更新用户失败！");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteUser(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>();
        if (!checkUserExistById(id)) {
            putMsg(map, 400, "所更新用户不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int delete = userMapper.delete(queryWrapper);
        if (delete >= 1) {
            putMsg(map, Status.SUCCESS.getCode(), "删除用户成功！");
        } else {
            putMsg(map, 400, "删除用户失败！");
        }
        return map;
    }


    /**
     * 检查用户是否重复
     *
     * @param user
     * @return
     */
    @Override
    public boolean checkUserExistByIdName(int id, User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("type", user.getType());
        queryWrapper.ne("id", id);
        return userMapper.exists(queryWrapper);
    }

    /**
     * 检查用户是否重复
     *
     * @param user
     * @return
     */
    @Override
    public boolean checkUserExistByUserName(User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("type", user.getType());
        return userMapper.exists(queryWrapper);
    }

    /**
     * 检查用户是否重复
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkUserExistById(int id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return userMapper.exists(queryWrapper);
    }

    /**
     * 加密
     *
     * @param user
     * @return
     */
    private User encipher(User user) {
        String passwordFromArgon2 = Argon2PasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordFromArgon2);
        return user;
    }

    private static boolean validateUserName(User user) {
        if (user == null) {
            return false;
        }
        String username = user.getUsername();
        String nickname = user.getNickname();
        // 校验 username 和 nickname 不为空，并且没有空格
        if (StringUtils.isBlank(username) || StringUtils.isBlank(nickname)
                || StringUtils.containsWhitespace(username) || StringUtils.containsWhitespace(nickname)) {
            return false;
        }
        return true;
    }
}
