package com.zlht.pbr.algorithm.management.tools.service;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * 基于argon2-jvm
 */
public class Argon2PasswordEncoder {

    /*
     * 设置默认推荐参数
     */
    private static final int ITERATIONS = 2;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    /*
     * 推荐 argon2id类型共有三种类型可选（ARGON2i,ARGON2d,ARGON2id;）
     */
    public static final Argon2Factory.Argon2Types TYPE = Argon2Factory.Argon2Types.ARGON2id;

    /*
     * 工厂模式获取单例Argon2对象
     */
    private static final Argon2 INSTANCE = Argon2Factory.create(TYPE);

    /**
     * 使用argon2id加密密码
     */

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
