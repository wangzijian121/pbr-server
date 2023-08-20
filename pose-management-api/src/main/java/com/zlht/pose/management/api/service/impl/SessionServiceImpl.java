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

package com.zlht.pose.management.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlht.pose.enums.Status;
import com.zlht.pose.management.api.service.SessionServiceI;
import com.zlht.pose.management.dao.entity.Session;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.SessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * session service implement
 */
@Service
public class SessionServiceImpl extends BaseServiceImpl implements SessionServiceI {

    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Autowired
    private SessionMapper sessionMapper;

    /**
     * get user session from request
     *
     * @param request request
     * @return session
     */
//    @Override
/*    public Session getSession(HttpServletRequest request) {
        String sessionId = request.getHeader("sessionId");

        if (StringUtils.isBlank(sessionId)) {
            Cookie cookie = WebUtils.getCookie(request, "sessionId");

            if (cookie != null) {
                sessionId = cookie.getValue();
            }
        }

        if (StringUtils.isBlank(sessionId)) {
            return null;
        }

        String ip = BaseController.getClientIpAddress(request);
        logger.debug("get session: {}, ip: {}", sessionId, ip);

        return sessionMapper.selectById(sessionId);
    }*/

    /**
     * create session
     *
     * @param user user
     * @param ip   ip
     * @return session string
     */
    @Override
    @Transactional
    public String createSession(User user, String ip) {
        Session session = null;

        // login
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("ip", ip);
        int delete = sessionMapper.delete(queryWrapper);
        if (delete >= 0) {
            Date now = new Date();
            // assign new session
            session = new Session();
            session.setId(UUID.randomUUID().toString());
            session.setIp(ip);
            session.setUserId(user.getId());
            session.setLastLoginTime(now);
            sessionMapper.insert(session);
            return session.getId();
        } else {
            return null;
        }
    }

    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip        no use
     * @param loginUser login user
     */
    @Override
    public Map<String, Object> signOut(String ip, User loginUser) {
        Map<String, Object> map =new HashMap<>();

        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id", loginUser.getId());
            queryWrapper.eq("ip", ip);
            Session session = sessionMapper.selectOne(queryWrapper);
            //delete session
            int  delete=sessionMapper.deleteById(session.getId());
            if (delete>0){
                putMsg(map,Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                return map;
            }
        } catch (Exception e) {
            logger.warn("userId : {} , ip : {} , find more one session", loginUser.getId(), ip);
        }
        return map;
    }
}
