package com.zlht.pose.management.tools.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Argon2PasswordEncoderTest {

    @Test
    public void testEncode() {
        String password = "hello world";
        String encodedPassword = Argon2PasswordEncoder.encode(password);
        assertNotNull(encodedPassword);
    }

    @Test
    public void testMatches() {
        String password = "hello world";
        String encodedPassword = Argon2PasswordEncoder.encode(password);
        assertTrue(Argon2PasswordEncoder.matches(encodedPassword, password));
        assertFalse(Argon2PasswordEncoder.matches(encodedPassword, "wrong password"));
    }
}