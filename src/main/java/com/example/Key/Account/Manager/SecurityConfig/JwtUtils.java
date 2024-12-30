package com.example.Key.Account.Manager.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    //    private final String jwtSecret = "X1tZzYURX0vV7JzAa4s3GdfYk3U+9D6xDwD2PVQZhlE=";
    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtExpirationMs = 86400000; // 24 hours

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}