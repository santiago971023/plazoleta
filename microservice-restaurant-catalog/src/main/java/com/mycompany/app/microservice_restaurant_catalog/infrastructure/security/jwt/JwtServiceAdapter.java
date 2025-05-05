package com.mycompany.app.microservice_restaurant_catalog.infrastructure.security.jwt;

import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.TokenServicePort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
@RequiredArgsConstructor
public class JwtServiceAdapter implements TokenServicePort {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key signingKey;

    @PostConstruct
    public void init(){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractSubjectFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
            String subject = claims.getSubject();
            return claims.getSubject();
        } catch (Exception e){
            return null;
        }
    }
}
