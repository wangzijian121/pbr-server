package com.zlht.pbr.algorithm.management.api.management.service;

import com.zlht.pbr.algorithm.management.dao.entity.User;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface SessionServiceI {

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
     *
     * @param ip
     * @param loginUser
     * @return
     */
    Map<String, Object> signOut(String ip, User loginUser);
}
