package com.microservice_user.application.ports.out;

import com.microservice_user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenServicePort {

    // Método para generar un token para un user dado
    String generateToken(User user);

    // Método para extraer el usuario desde el token
    String extractSubjectFromToken(String token);

    // Método que valida si el token es válido
    boolean isValidToken(String token, UserDetails userDetails);

}
