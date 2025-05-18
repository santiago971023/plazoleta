package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface TokenServicePort {

    // Método para extraer el usuario desde el token
    String extractSubjectFromToken(String token);


    List<String> extractRolesFromToken(String token);



}
