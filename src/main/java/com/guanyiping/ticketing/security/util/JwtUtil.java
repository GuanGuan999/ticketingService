package com.guanyiping.ticketing.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private Long expirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // create Token
    public String generateToken(Authentication authentication) {
        // Implementation for generating JWT token
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, key())
                .compact();
    }

    public boolean validateToken(String token, String email) {
        String tokenEmail = getClaims(token).getSubject();
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }

    // analyze Token
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(key()).build()
                    .parseClaimsJws(token)
                    .getBody();

    }

}
