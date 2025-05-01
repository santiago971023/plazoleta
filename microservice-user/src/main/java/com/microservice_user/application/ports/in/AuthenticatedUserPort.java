package com.microservice_user.application.ports.in;

// Esta interfaz será un puerto de entrada.
// La necesito porque mi capa de aplicación necesita una forma de obtener la información sobre el user que inició sesión
// como su ID o sus roles. Esto es porque esa información está en SecurityContextHolder de la capa de infraestructura
// Por ende no puedo inyectar directamente SecurityContextHolder aquí porque mi capa de aplicación no puede depender de una capa externa

// Puerto de Entrada para la capa de Aplicacion. Define el contrato para obtener informacion del user autenticado sin depender de frameworks.

public interface AuthenticatedUserPort {

    Long authenticatedUserId();
    String getAuthenticatedUserEmail();
    boolean hasRole(String role);
    AuthenticatedUserDetails getAuthenticatedUserDetails();

}
