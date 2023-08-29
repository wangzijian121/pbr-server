package com.zlht.pbr.algorithm.management.tools.service;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * 基于argon2-jvm
 *
 * @author zi jian Wang
 */

public class Argon2PasswordEncoder {


    private static final int ITERATIONS = 2;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    public static final Argon2Factory.Argon2Types TYPE = Argon2Factory.Argon2Types.ARGON2id;
    private static final Argon2 INSTANCE = Argon2Factory.create(TYPE);


    public static String encode(String password) {
        return INSTANCE.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray());
    }

    /**
     * 使用argon2id算法验证密码是否正确
     *
     * @param encodedPassword 加密后密码
     */
    public static boolean matches(String encodedPassword, String password) {
        return INSTANCE.verify(encodedPassword, password.toCharArray());
    }
}
