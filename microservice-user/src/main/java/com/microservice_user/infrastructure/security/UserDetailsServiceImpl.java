package com.microservice_user.infrastructure.security;

import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.exception.ErrorMessagesInfrastructure;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Adaptador que implementa la interfaz 'UserDetailsService' de Spring Security. Sabe como cargar los detalles de un user (email, clave, roles) desde el repositorio de dominio.
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;
    // Si usas SLF4J:
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando cargar usuario con email: " + username); // Log 1

        User userDomain = userRepositoryPort.findByEmail(username)
                .orElseThrow( () -> {
                    System.out.println("Usuario NO encontrado con email: " + username); // Log 2a
                    return new UsernameNotFoundException("Usuario no encontrado con email: " + username);
                });
//        User userDomain = userRepositoryPort.findByEmail(username)
//                .orElseThrow( () -> new UsernameNotFoundException(ErrorMessagesInfrastructure.USER_NOT_FOUND_WITH_EMAIL.getMessage()));

        System.out.println("Usuario encontrado: " + userDomain.getEmail() + ", Rol: " + userDomain.getRole()); // Log 2b

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userDomain.getRole().name())));

        if (userDomain.getRole() != null) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userDomain.getRole().name())));
            System.out.println("Autoridad(es) asignada(s): " + authorityList); // Log 3a
        } else {
            System.out.println("El usuario encontrado NO tiene un rol asignado."); // Log 3b
        }

        return new CustomSpringUserDetails(
                userDomain.getId(),
                userDomain.getEmail(),
                userDomain.getPassword(),
                authorityList
                // Spring Security tambien tiene flags para si la cuenta esta habilitada, no expirada, etc.
                // Por simplicidad inicial, asumiremos que siempre estan true
                // true, // isAccountNonExpired
                // true, // isAccountNonLocked
                // true, // isCredentialsNonExpired
                // true // isEnabled

        );
    }
}
