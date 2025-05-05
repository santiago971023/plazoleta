package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenServicePort {

    // Método para extraer el usuario desde el token
    String extractSubjectFromToken(String token);



}
