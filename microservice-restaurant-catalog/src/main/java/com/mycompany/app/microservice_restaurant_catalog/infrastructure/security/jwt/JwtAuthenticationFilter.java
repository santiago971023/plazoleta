package com.mycompany.app.microservice_restaurant_catalog.infrastructure.security.jwt;

import com.mycompany.app.microservice_restaurant_catalog.application.ports.out.TokenServicePort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger; // Importa la interfaz Logger
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class); // <--- Añade esto


    private final TokenServicePort tokenServicePort;

    public JwtAuthenticationFilter(TokenServicePort tokenServicePort) {
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.debug("Entering JwtAuthenticationFilter for request: {} {}", request.getMethod(), request.getRequestURI()); // <--- Log al inicio
        

        // Obtenemos el header de authenticación
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("authHeader = " + authHeader);
        final String jwt;
        final String userEmail; // usaremos este email como subject

        // verificamos si el header existe y tiene el formato de "Bearer..."
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            log.debug("Authorization header missing or not starting with Bearer. Passing to next filter."); // <--- Log si no hay token

            // Si no hay token en el formato esperado, se pasa al siguiente filtro
            filterChain.doFilter(request, response);
            return;
        }
        // Se extrae el token removiendo "Bearer "
        jwt = authHeader.substring(7);
        log.debug("Extracted JWT: {}", jwt); // <--- Log el token extraido

        // Se extrae el email del usuario del token usando el tokenServicePort
        userEmail = tokenServicePort.extractSubjectFromToken(jwt);
        log.debug("Extracted user email from token: {}", userEmail); // <--- Log el email extraido (puede ser null)


        // Si el email se extrajo correctamente y no hay autenticación en el contexto de seguridad actual
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            log.debug("User email found and SecurityContextHolder is empty. Attempting to authenticate."); // <--- Log antes de autenticar

            // Creo un objeto UserDetails basico para SpringSecurity usando el email del token y le cargamos los roles si los encontramos
            UserDetails userDetails = new User(userEmail, "", new ArrayList<>());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            log.debug("Setting authentication in SecurityContextHolder for user: {}", userEmail); // <--- Log antes de setear

            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.debug("SecurityContextHolder updated.");
        }
        filterChain.doFilter(request, response);



    }
}
