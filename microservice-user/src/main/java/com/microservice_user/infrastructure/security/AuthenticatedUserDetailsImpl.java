package com.microservice_user.infrastructure.security;

import com.microservice_user.application.ports.in.AuthenticatedUserDetails;
import lombok.RequiredArgsConstructor;

import java.util.List;



// Implementacion concreta de la interfaz 'AuthenticatedUserDetails' (de la capa de Aplicacion).
// Es una clase de datos en Infraestructura que se usa para transportar la info del user autenticado de vuelta a la Aplicacion a traves del puerto.
 @RequiredArgsConstructor
public class AuthenticatedUserDetailsImpl implements AuthenticatedUserDetails {

    private final Long userId;

    private final String email;

    private final List<String> roles;



    @Override public Long getUserId() {
        return userId;
    }

    @Override public String getEmail() {
        return email;
    }

    @Override public List<String> getRoles() {
        return roles;
    }
}
