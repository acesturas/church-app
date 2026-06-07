package com.application.church.global.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER =
            new BCryptPasswordEncoder();

    public static String hash(String password) {
        return ENCODER.encode(password);
    }

    public static boolean matches(
            String rawPassword,
            String hashedPassword) {

        return ENCODER.matches(
                rawPassword,
                hashedPassword);
    }
}