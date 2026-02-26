package com.nimbletech.petadopt.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    public static final String ACCESS_SECRET_KEY = "8fX2kPqW3mN7rT5vY9zA1bC4dE6fG8hJ0iL2nO4pQ6sU8wX0yZ2";
    public static final String REFRESH_SECRET_KEY = "kM9nB3vR7tY1uI5oP2aS6dF8gH0jL4nQ6wE2xZ5cV9mB1rT4yU8";
    private static final long ACCESS_EXPIRATION_TIME = 300_000;  // 5 minutes (5 * 60 * 1000)
    private static final long REFRESH_EXPIRATION_TIME = 604_800_000;  // 7 days (7 * 24 * 60 * 60 * 1000)

    public String generateAccessToken(String username, Long userId, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        claims.put("id", userId);

        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, ACCESS_SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, REFRESH_SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String extractUsername(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateAccessToken(String token, String username) {
        return username.equals(extractUsername(token, ACCESS_SECRET_KEY)) && !isTokenExpired(token, ACCESS_SECRET_KEY);
    }

    public boolean validateRefreshToken(String token, String username) {
        return username.equals(extractUsername(token, REFRESH_SECRET_KEY)) && !isTokenExpired(token, REFRESH_SECRET_KEY);
    }

    private boolean isTokenExpired(String token, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();

            Date expiry = claims.getExpiration();
            return expiry.before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return true;
        }
    }
}
