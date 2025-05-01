package com.microservice_user.infrastructure.security.jwt;

import com.microservice_user.application.ports.out.TokenServicePort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final TokenServicePort tokenServicePort;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, TokenServicePort tokenServicePort) {
        this.userDetailsService = userDetailsService;
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Obtenemos la cabecera Authorization
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Declaro variables para el token y el identificador del user
        final String jwt;
        final String userEmail;

        // Verifico que la cabecera de authorization existe y tiene l formato de "Bearer {TOKEN}"
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            // Sino hay token o no tiene el formato esperado, paso al siguiente filtro
            filterChain.doFilter(request, response);
            return; //salgo del método
        }

        // Si la cabcera es correcta, extraer el token
        jwt = authHeader.substring(7);

        try {
            userEmail = tokenServicePort.extractSubjectFromToken(jwt);

            // Si userEmail no es null y el usuario NO esta ya autenticado...
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (tokenServicePort.isValidToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e ){
            System.out.println("Error al procesar token JWT " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
