package com.zlht.pose.management.tools.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateServiceTest {

    @Test
    public void testValidEmail() {
        String email = "example@example.com";
        boolean isValid = ValidateService.validateEmail(email);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidEmail() {
        String email = "example@example";
        boolean isValid = ValidateService.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void testNullEmail() {
        String email = null;
        boolean isValid = ValidateService.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void testEmptyEmail() {
        String email = "";
        boolean isValid = ValidateService.validateEmail(email);
        assertFalse(isValid);
    }

    @Test
    public void testWhitespaceEmail() {
        String email = "   ";
        boolean isValid = ValidateService.validateEmail(email);
        assertFalse(isValid);
    }
}