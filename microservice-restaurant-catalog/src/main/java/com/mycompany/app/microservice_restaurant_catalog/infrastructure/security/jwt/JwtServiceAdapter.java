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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger; // Importa la interfaz Logger
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class JwtServiceAdapter implements TokenServicePort {

    private static final Logger log = LoggerFactory.getLogger(JwtServiceAdapter.class); // <--- Añade este logger


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

    @Override
    public List<String> extractRolesFromToken(String token) {
        log.debug("Attempting to extract roles from token."); // <--- Log al inicio del metodo

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Obtener el claim "roles"
            Object rolesClaim = claims.get("roles"); // <--- Obtener como Object por si el tipo no es String
            log.debug("Raw 'roles' claim from token: {}", rolesClaim); // <--- Log el claim crudo

            String rolesString = null;
            if (rolesClaim instanceof String) { // Verificar si es una cadena
                rolesString = (String) rolesClaim;
                log.debug("'roles' claim is a String: {}", rolesString); // <--- Log si es cadena
            } else {
                log.debug("'roles' claim is NOT a String, its type is: {}", (rolesClaim != null ? rolesClaim.getClass().getName() : "null")); // <--- Log si no es cadena
            }


            if (rolesString != null && !rolesString.trim().isEmpty()) {
                log.debug("Splitting roles string: '{}' by comma.", rolesString); // <--- Log antes de dividir
                // Dividir la cadena por coma
                List<String> rolesList = Arrays.stream(rolesString.split(","))
                        .map(String::trim)
                        .filter(role -> !role.isEmpty())
                        .collect(Collectors.toList());
                log.debug("Successfully extracted and processed roles: {}", rolesList); // <--- Log la lista final de roles
                return rolesList;
            } else {
                log.debug("Roles string is null or empty after extraction/trimming."); // <--- Log si la cadena es nula o vacia
                return Collections.emptyList();
            }

        } catch (Exception e) {
            log.error("Error extracting roles from token: {}", e.getMessage(), e); // <--- Log errores con stacktrace
            return Collections.emptyList();

        }

    }
}
