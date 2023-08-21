package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;

import java.util.Map;

public interface SessionServiceI {

    /**
     * get user session from request
     *
     * @param request request
     * @return session
     */
//    Session getSession(HttpServletRequest request);

    /**
     * create session
     *
     * @param user user
     * @param ip   ip
     * @return session string
     */
    String createSession(User user, String ip);


    /**
     * sign out
     * remove ip restrictions
     *
     * @param ip        no use
     * @param loginUser login user
     */
    Map<String, Object> signOut(String ip, User loginUser);
}
