package com.microservice_user.infrastructure.security;

import com.microservice_user.application.ports.in.AuthenticatedUserDetails;
import com.microservice_user.application.ports.in.AuthenticatedUserPort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;


// Adaptador que implementa el Puerto de Entrada 'AuthenticatedUserPort'. Sabe como obtener la informacion del user autenticado desde el SecurityContextHolder (usando CustomSpringUserDetails) y mapearla a la interfaz AuthenticatedUserDetails de la Aplicacion.

@Component
public class AuthenticatedUserAdapter implements AuthenticatedUserPort {

    private UserDetails getAuthenticationUserDetailsFromContext(){ // Pilas, retorna un UserDetails
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            // Si no hay autenticación o no está auntenticado, lanza exception
            throw new AccessDeniedException("User not authenticated");
        }
        Object principal = authentication.getPrincipal();

        // Debo verificar que principal sea una instancia de mi clase personalizada
        if(principal instanceof  CustomSpringUserDetails){
            return (UserDetails) principal;
        }else{
            // Si principal no es del tipo esperado...
            throw new IllegalStateException("Principal no es instancia de CustomSpringUserDetails");
        }
    }



    @Override
    public Long authenticatedUserId() {
        CustomSpringUserDetails userDetails = (CustomSpringUserDetails) getAuthenticatedUserDetails();
        return userDetails.getUserId();
    }

    @Override
    public String getAuthenticatedUserEmail() {
        UserDetails userDetails = getAuthenticationUserDetailsFromContext();
        return userDetails.getUsername();
    }

    @Override
    public boolean hasRole(String role) {
        UserDetails userDetails = getAuthenticationUserDetailsFromContext();
        return userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + role));
    }

    @Override
    public AuthenticatedUserDetails getAuthenticatedUserDetails() {
        CustomSpringUserDetails springUserDetails = (CustomSpringUserDetails) getAuthenticationUserDetailsFromContext();
        Long userId = springUserDetails.getUserId();
        String email = springUserDetails.getUsername();
        List<String> roles = springUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.replace("ROLE_", ""))
                .toList();
        return new AuthenticatedUserDetailsImpl(userId, email, roles);
    }


}
