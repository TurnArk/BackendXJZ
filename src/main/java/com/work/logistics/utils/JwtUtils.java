package com.work.logistics.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // JWT 密钥
    private static final String SECRET = "PCBBoardDetection@2025-4-23@114yajyusenpai514###1919810!";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 过期时间：1 天
    private static final long EXPIRATION = 86400000;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 生成 Token，包含 id 和 role
     */
    public static String generateToken(String id, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    /**
     * 解析 Token，返回包含 id 和 role 的 Map
     */
    public static Map<String, String> parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String id = claims.get("id", String.class);
        String role = claims.get("role", String.class);

        Map<String, String> result = new HashMap<>();
        result.put("id", id);
        result.put("role", role);
        return result;
    }

    /**
     * 获取用户id
     */
    public static String getUserId(String token) {
        return parseToken(token).get("id");
    }

    /**
     * 获取用户权限
     */
    public static String getUserRole(String token) {
        return parseToken(token).get("role");
    }

    /**
     * 密码加密
     */
    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 密码验证
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}