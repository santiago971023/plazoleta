package com.microservice_user.application.ports.in;

import java.util.List;
// Esta interfaz define los detalles del user autenticado que la capa de Aplicacion necesita.
// ¡No depende de org.springframework.security.core.userdetails.UserDetails!

public interface AuthenticatedUserDetails {

    Long getUserId();
    String getEmail();
    List<String> getRoles();

}
