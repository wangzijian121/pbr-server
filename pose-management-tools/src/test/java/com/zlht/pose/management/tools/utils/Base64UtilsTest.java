package com.zlht.pose.management.tools.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base64UtilsTest {

    @Test
    public void testEncode() {
        String input = "hello world";
        String expected = "aGVsbG8gd29ybGQ=";
        String encoded = Base64Utils.encode(input);
        assertEquals(expected, encoded);
    }

    @Test
    public void testDecode() {
        String input = "aGVsbG8gd29ybGQ=";
        String expected = "hello world";
        String decoded = Base64Utils.decode(input);
        assertEquals(expected, decoded);
    }

    @Test
    public void testEncodeDecode() {
        String input = "hello world";
        String encoded = Base64Utils.encode(input);
        String decoded = Base64Utils.decode(encoded);
        assertEquals(input, decoded);
    }
}