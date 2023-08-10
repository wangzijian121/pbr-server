/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zlht.pose.management.api.security.impl;

import com.zlht.pose.management.api.enums.Status;
import com.zlht.pose.management.api.security.Authenticator;
import com.zlht.pose.management.api.service.SessionServiceI;
import com.zlht.pose.management.api.service.impl.BaseServiceImpl;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.UserMapper;
import com.zlht.pose.management.tools.service.Argon2PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AbstractAuthenticator extends BaseServiceImpl<User> implements Authenticator {
    private static final Logger logger = LoggerFactory.getLogger(AbstractAuthenticator.class);

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    private SessionServiceI sessionServiceI;


    @Override
    public Map<String, Object> authenticate(String username, String password, String ip) {
        Map<String, Object> map = new HashMap<>();
        if (username == null) {
            putMsg(map, 400, "请输入用户名！");
            return map;
        }
        if (password == null) {
            putMsg(map, 400, "请输入密码！");
            return map;
        }
        User user = userMapper.queryUserByUserName(username);
        if (user != null) {
            String encipherPassword = user.getPassword();
            boolean check = Argon2PasswordEncoder.matches(encipherPassword, password);
            if (check) {
                String session_id = sessionServiceI.createSession(user, ip);
                if (session_id == null) {
                    putMsg(map, 400, "session创建错误,登录失败!");
                    return map;
                }
                putMsg(map, Status.SUCCESS.getCode(), "登录成功！");
                map.put("data", session_id);
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