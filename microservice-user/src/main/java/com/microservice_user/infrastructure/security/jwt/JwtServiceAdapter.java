package com.microservice_user.infrastructure.security.jwt;

import com.microservice_user.application.ports.out.TokenServicePort;
import com.microservice_user.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
// Importa Logger si usas SLF4J
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;


@Component
public class JwtServiceAdapter implements TokenServicePort {

    // Si usas SLF4J:
    private static final Logger log = LoggerFactory.getLogger(JwtServiceAdapter.class);

    @Value("${jwt.secret.key}")
    private String secretKey;

    private java.security.Key signingKey;

    @PostConstruct   // Este metodo se ejecuta después de la inyección de dependencias
    public void init(){
        // convertir la clave secreta de String a bytes
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        // Usa JJWT (Keys.hmacShaKeyFor) para crear el objeto Key a partir de los bytes
        signingKey = Keys.hmacShaKeyFor(keyBytes); // Asigna el objeto Key al campo signingKey
        System.out.println("Clave de firma JWT inicializada. Hashcode: " + signingKey.hashCode());
    }



    @Override
    public String generateToken(User user) {
        // Importa esta clase
        // import io.jsonwebtoken.Jwts;

        // 1. Definir los claims (informacion del usuario)
        // Puedes poner el ID del usuario o el email como subject, lo que prefieras para identificar al usuario
        String subject = user.getEmail(); // O user.getId().toString();
        System.out.println("Generando token para usuario: " + user.getEmail() + ", Subject a usar: " + subject); // <-- Log aqui



        // Puedes poner los roles en un claim. Por ejemplo, como un string separado por comas
        String rolesClaim = user.getRole().name(); // Si solo tiene un rol, o construir un string si tuviera varios

        // Crear un mapa de claims si tienes varios claims ademas del subject
        java.util.Map<String, Object> claims = new java.util.HashMap<>();
//        claims.put("sub", user.getEmail());
//        claims.put("roles", user.getRole().name()); // Nombre del claim puede ser "roles" o "authorities"


        // 2. Definir fechas de emision y expiracion
        java.util.Date issuedAt = new java.util.Date(); // Fecha actual

        // Define el tiempo de vida del token (ej. 24 horas en milisegundos)
        long expirationTimeMillis = 1000 * 60 * 60 * 24; // 24 horas
        java.util.Date expiration = new java.util.Date(issuedAt.getTime() + expirationTimeMillis);


        // 3. Construir el token usando Jwts.builder()
        String token = Jwts.builder()
                .claim("sub", subject) // <-- Añadir subject directamente
                .claim("roles", user.getRole().name())
                .setIssuedAt(issuedAt) // Cuando fue emitido
                .setExpiration(expiration) // Cuando expira
                .signWith(this.signingKey) // <-- FIRMA el token con la clave que preparamos
                .compact(); // Finaliza la construccion y obtiene el String

        System.out.println("Token generado exitosamente."); // Log al final
        return token; // Retorna el token generado
    }



    @Override
    public String extractSubjectFromToken(String token) {
        try {
            System.out.println("Intentando extraer subject del token...");
            System.out.println("Using signingKey hashcode for validation: " + signingKey.hashCode()); // <-- Log el hash en validacion
            Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
            String subject = claims.getSubject();
            System.out.println("token : " + token);
            System.out.println("Claims obtenidos del token: " + claims);
            System.out.println("Subject extraido exitosamente: " + subject);
            return claims.getSubject();
        } catch (Exception e){
            System.out.println("Error al validar token JWT (extractSubjectFromToken): " + e.getMessage()); // Log C
            System.out.println("Error al validad token JWT" + e.getMessage());
            return null;
        }

    }

    @Override
    public boolean isValidToken(String token, UserDetails userDetails) {
        System.out.println("Llamando a isValidToken..."); // Log D
        try{
            final String subject = extractSubjectFromToken(token);
            System.out.println("Validando sujeto contra UserDetails. Subject del token: " + subject + ", Username de UserDetails: " + userDetails.getUsername()); // Log E
            return (subject != null) && subject.equals(userDetails.getUsername());
        }catch (Exception e){
            System.out.println("Excepcion en isValidToken (esto no deberia pasar si extractSubjectFromToken captura bien): " + e.getMessage()); // Log F
            return false;
        }

    }
}
