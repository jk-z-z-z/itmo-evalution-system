package com.itmo.evaluationSystem.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 至少 32 字节的密钥用于 HS256
    private static final byte[] SECRET_BYTES = "evaluationSystemSecretKey-ChangeMe-AtLeast-32-Bytes-2025!".getBytes(StandardCharsets.UTF_8);
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_BYTES);

    private static final long EXPIRATION_TIME = 3600 * 1000 * 12; // 12 小时

    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static void parseJwt(String token) throws ExpiredJwtException {
        Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
