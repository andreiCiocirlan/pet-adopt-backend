package com.nimbletech.petadopt.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "k9fZr8/mrACV2Sr+Tf6vWXFJ3QWJfVqfPGEvVYcJBP8=";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String generateToken(String username, Long userId) {
        return Jwts.builder()
                   .setSubject(username)
                   .claim("id", userId)
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                   .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                   .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET_KEY)
                   .parseClaimsJws(token)
                   .getBody()
                   .getExpiration()
                   .before(new Date());
    }
}
