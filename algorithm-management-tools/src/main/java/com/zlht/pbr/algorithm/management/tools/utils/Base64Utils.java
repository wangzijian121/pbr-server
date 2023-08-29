package com.zlht.pbr.algorithm.management.tools.utils;

import java.util.Base64;

/**
 * @author zi jian Wang
 */
public class Base64Utils {
    /**
     * 将字符串进行 Base64 编码
     *
     * @param input 原始字符串
     * @return Base64 编码字符串
     */
    public static String encode(String input) {
        byte[] bytes = input.getBytes();
        byte[] encodedBytes = Base64.getEncoder().encode(bytes);
        return new String(encodedBytes);
    }

    /**
     * 将 Base64 编码字符串进行解码
     *
     * @param input Base64 编码字符串
     * @return 原始字符串
     */
    public static String decode(String input) {
        byte[] bytes = input.getBytes();
        byte[] decodedBytes = Base64.getDecoder().decode(bytes);
        return new String(decodedBytes);
    }
}