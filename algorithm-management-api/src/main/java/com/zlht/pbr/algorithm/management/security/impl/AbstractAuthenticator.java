package com.zlht.pbr.algorithm.management.security.impl;

import com.zlht.pbr.algorithm.management.api.management.service.SessionServiceI;
import com.zlht.pbr.algorithm.management.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.management.dao.entity.User;
import com.zlht.pbr.algorithm.management.dao.mapper.UserMapper;
import com.zlht.pbr.algorithm.management.enums.Status;
import com.zlht.pbr.algorithm.management.security.Authenticator;
import com.zlht.pbr.algorithm.management.tools.service.Argon2PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class AbstractAuthenticator extends BaseServiceImpl<User> implements Authenticator {
    private static final Logger logger = LogManager.getLogger(AbstractAuthenticator.class);

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    private SessionServiceI sessionServiceI;


    @Override
    public Map<String, Object> authenticate(HttpServletResponse response, String username, String password, String ip, int userType) {

        logger.info("authenticate() method. username={}, ip={}, userType={}", username, ip, userType);
        Map<String, Object> map = new HashMap<>();
        if (username == null) {
            putMsg(map, 400, "请输入用户名！");
            return map;
        }
        if (password == null) {
            putMsg(map, 400, "请输入密码！");
            return map;
        }
        User user = userMapper.queryUserByUserName(username, userType);

        if (user != null) {
            if (user.getType() != userType) {
                putMsg(map, 401, "非本平台用户,无登录权限!");
                return map;
            }
            String encipherPassword = user.getPassword();
            boolean check = Argon2PasswordEncoder.matches(encipherPassword, password);
            if (check) {
                String sessionId = sessionServiceI.createSession(user, ip);
                if (sessionId == null) {
                    putMsg(map, 400, "session创建错误,登录失败!");
                    return map;
                }
                putMsg(map, Status.SUCCESS.getCode(), "登录成功！");
                map.put("nickname", user.getNickname());
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("sessionId", sessionId);
                userMap.put("nickname", user.getNickname());
                userMap.put("id", user.getId());

                map.put("data", userMap);
                Cookie cookie = new Cookie("sessionId", sessionId);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            } else {
                putMsg(map, 400, "用户名或密码错误！");
                return map;
            }
        } else {
            putMsg(map, 400, "用户名或密码错误！");
        }
        return map;
    }


}
