package com.zlht.pbr.algorithm.management.tools.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zi jian Wang
 */
public class ValidateService {

    /**
     * 校验邮箱 工具类
     *
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
