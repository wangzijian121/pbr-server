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
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zlht.pose.management.api.service.SessionServiceI;
import com.zlht.pose.management.dao.entity.Session;
import com.zlht.pose.management.dao.entity.User;
import com.zlht.pose.management.dao.mapper.SessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

        // logined
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", user.getId());
        List<Session> sessionList = sessionMapper.selectList(queryWrapper);
        Date now = new Date();

        /**
         *  清理已经存在的session
         */
        if (CollectionUtils.isNotEmpty(sessionList)) {

            if (sessionList.size() > 1) {
                for (int i = 1; i < sessionList.size(); i++) {
                    sessionMapper.deleteById(sessionList.get(i).getId());
                }
            }
        }
        // assign new session
        session = new Session();

        session.setId(UUID.randomUUID().toString());
        session.setIp(ip);
        session.setUserId(user.getId());
        session.setLastLoginTime(now);

        sessionMapper.insert(session);

        return session.getId();
    }

    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip        no use
     * @param loginUser login user
     */
//    @Override
/*
    public void signOut(String ip, User loginUser) {
        try {
            */
/**
 * query session by user id and ip
 *//*

            Session session = sessionMapper.queryByUserIdAndIp(loginUser.getId(), ip);

            //delete session
            sessionMapper.deleteById(session.getId());
        } catch (Exception e) {
            logger.warn("userId : {} , ip : {} , find more one session", loginUser.getId(), ip);
        }
    }
*/

}
