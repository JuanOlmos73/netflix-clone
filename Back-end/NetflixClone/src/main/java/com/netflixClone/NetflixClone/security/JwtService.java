package com.netflixClone.NetflixClone.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

  // Método para extraer todos los claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(Map<String, Object> extras, String subject, long expTime) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(extras)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expTime))
                .signWith(getSignInKey())
                .compact();
    }

    // Métodos adicionales necesarios
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String generateToken(String subject) {
        return generateToken(Map.of(), subject, jwtExpiration);
    }

    public String generateRefreshToken(String subject) {
        return generateToken(Map.of(), subject, refreshExpiration);
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !extractAllClaims(token).getExpiration().before(new Date());
    }

       private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean validateToken(String token) {
    try {
        extractAllClaims(token); // Esto lanza excepción si el token está mal formado
        return !isTokenExpired(token);
    } catch (Exception e) {
        return false;
    }
}
}
