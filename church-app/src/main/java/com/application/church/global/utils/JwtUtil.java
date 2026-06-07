package com.application.church.global.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final SecretKey KEY =
        Keys.hmacShaKeyFor(
            "mySecretKey123456789mySecretKey123456789"
                .getBytes()
        );

    public static String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                    new Date(
                        System.currentTimeMillis()
                        + 86400000
                    )
                )
                .signWith(KEY)
                .compact();
    }

    public static String getUsername(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}