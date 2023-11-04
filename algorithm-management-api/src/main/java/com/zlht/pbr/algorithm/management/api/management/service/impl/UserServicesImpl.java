package com.zlht.pbr.algorithm.management.api.management.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlht.pbr.algorithm.management.api.management.service.SessionServiceI;
import com.zlht.pbr.algorithm.management.api.management.service.UserServicesI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.UserMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.tools.service.Argon2PasswordEncoder;
import com.zlht.pbr.algorithm.management.utils.PageInfo;
import com.zlht.pbr.algorithm.management.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zi jian Wang
 */
@Service
public class UserServicesImpl extends BaseServiceImpl<User> implements UserServicesI {

    private static final Logger logger = LogManager.getLogger(UserServicesImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SessionServiceI sessionServiceI;


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
        if (type != -1) {
            wapper.eq("type", type);
        }
        if (keyword != null) {
            wapper.like("nickname", keyword);
        }
        wapper.orderByDesc("create_time");
        Page<User> userPage = userMapper.selectPage(page, wapper);
        logger.info("queryUserList() method. username={},type={}, currentPage={},pageSize={},keyword={}",
                loginUser.getUsername(), type, currentPage, pageSize, keyword);
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

        Map<String, Object> map = new HashMap<>(3);
        if (!canOperator(loginUser)) {
            putMsg(map, Status.USER_NO_OPERATION_PERM.getCode(), Status.USER_NO_OPERATION_PERM.getMsg());
            return map;
        }
        if (checkUserExistByUserName(user)) {
            putMsg(map, 400, "用户已存在！");
            return map;
        }
        int checkType = 2;
        if (user.getType() != checkType && !validateUserName(user)) {
            putMsg(map, 400, "用户名或昵称不符合规范！");
            return map;
        }
        //管理员的话加密 密码
        try {
            userMapper.insert(encipher(user));
            putMsg(map, Status.SUCCESS.getCode(), "新建用户成功！");
        } catch (Exception e) {
            String errMsg = "新建用户失败";
            logger.error("createUser() method .message={}, username={}", errMsg, user.getUsername(), e);
            putMsg(map, 400, errMsg);
        }

        return map;
    }


    @Override
    public Map<String, Object> updateUser(User loginUser, int id, User user) {

        Map<String, Object> map = new HashMap<>(3);
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
        try {
            userMapper.update(encipher(user), queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "更新用户成功！");
        } catch (Exception e) {
            String errMsg = "更新用户失败";
            logger.error("updateUser() method .message={}, username={}", errMsg, user.getUsername(), e);
            putMsg(map, 400, errMsg);
        }

        return map;
    }

    @Override
    public Map<String, Object> deleteUser(User loginUser, int id) {
        Map<String, Object> map = new HashMap<>(3);
        if (!checkUserExistById(id)) {
            putMsg(map, 400, "所更新用户不存在！");
            return map;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        try {
            userMapper.delete(queryWrapper);
            putMsg(map, Status.SUCCESS.getCode(), "删除用户成功！");
        } catch (Exception e) {
            String errMsg = "删除用户失败";
            logger.error("deleteUser() method .message={}, id={}", errMsg, id, e);
            putMsg(map, 400, errMsg);
        }
        return map;
    }

    /**
     * 获取机构管理员映射
     *
     * @return
     */
    @Override
    public Result getInstitutionalAdminMap(User loginUser) {
        Result result = new Result();
        if (!canOperator(loginUser)) {
            result.setMsg(Status.USER_NO_OPERATION_PERM.getMsg());
            result.setCode(Status.USER_NO_OPERATION_PERM.getCode());
            return result;
        }
        List<Map<String, Object>> list = userMapper.queryInstitutionalAdminMap();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(list);
        return result;
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
