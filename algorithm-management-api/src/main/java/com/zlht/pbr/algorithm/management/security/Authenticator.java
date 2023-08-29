package com.zlht.pbr.algorithm.management.security;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface Authenticator {
    /**
     * 通过用户名和密码验证合法性
     *
     * @param response
     * @param username
     * @param password
     * @param ip
     * @param userType
     * @return
     */
    Map<String, Object> authenticate(HttpServletResponse response, String username, String password, String ip, int userType);

}
