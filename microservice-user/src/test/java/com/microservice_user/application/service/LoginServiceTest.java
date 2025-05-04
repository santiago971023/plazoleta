package com.microservice_user.application.service;

import com.microservice_user.application.DTOs.LoginRequestDTO;
import com.microservice_user.application.DTOs.LoginResponseDTO;
import com.microservice_user.application.exception.PasswordDoesNotMatchException;
import com.microservice_user.application.exception.UserNotFoundByEmailException;
import com.microservice_user.application.ports.out.TokenServicePort;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.application.services.LoginService;
import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenServicePort tokenServicePort;

    @InjectMocks
    private LoginService loginService;

    @Test
    @DisplayName("Debería lanzar una excepción 'PasswordDoesNotMatchException'")
    void testLogin_PasswordMisMatch(){
        // Preparo datos de entrada
        String testEmail = "test@example.com";
        String testPassword = "abdc12345";
        String encryptedPass = "encryptedPassword";

        // Given
        // Creo un objeto DTO de petición de login de prueba
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(testEmail);
        loginRequestDTO.setPassword(testPassword);

        // Creo un objeto User de dominio simulado como si viniera de la base de datos.
        User user = new User();
        user.setEmail(testEmail);
        user.setPassword(encryptedPass);
        user.setRole(RoleEnum.CLIENTE);
        user.setId(1L);

        // Defino el comportamiento de los mocks (el llamado)
        when(userRepositoryPort.findByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequestDTO.getPassword(), encryptedPass)).thenReturn(false);


        // When
        // Ejecutar método y esperar una excepción
        PasswordDoesNotMatchException thrown = assertThrows(PasswordDoesNotMatchException.class,
                () -> loginService.login(loginRequestDTO),
                "Debería lanzar PasswordDoesNotMatchExceptio cuando la contraseña ingresada no coincida con la contraseña en base de datos.");

        // Then
        verify(userRepositoryPort, times(1)).findByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
        verify(tokenServicePort, never()).generateToken(any());

    }


    @Test
    @DisplayName("Debería retornar una excepción 'UserNotFoundByEmailException'.")
    void testLogin_UserNotFound(){

        // Preparo datos de entrada
        String testEmail = "test@example.com";
        String testPassword = "abdc12345";


        // Given
        // Creo un objeto DTO de petición de login de prueba
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(testEmail);
        loginRequestDTO.setPassword(testPassword);



        // Defino el comportamiento de los Mocks (Simulo el llamado)
        when(userRepositoryPort.findByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.empty());

        // When
        // Ejecutar método y esperar una excepción
        UserNotFoundByEmailException thrown = assertThrows(UserNotFoundByEmailException.class,
                () -> loginService.login(loginRequestDTO),
                "Debería lanzar UserNotFoundByEmailException cuando no encuentre usuarios con el email indicado");

        // Then
        verify(userRepositoryPort, times(1)).findByEmail(anyString());
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(tokenServicePort, never()).generateToken(any());
    }

    @Test
    @DisplayName("Debería retornar un LoginResponseDTO")
    void testLogin_Succesful(){
        // Preparo datos de entrada
        String testEmail = "test@example.com";
        String testPassword = "abdc12345";
        String encryptedPass = "encryptedPassword";
        String expectedToken = "falseTokenForTest";

        // GIVEN

        // Creo un objeto DTO de petición de login de prueba
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(testEmail);
        loginRequestDTO.setPassword(testPassword);

        // Creo un objeto User de dominio simulado como si viniera de la base de datos.
        User user = new User();
        user.setEmail(testEmail);
        user.setPassword(encryptedPass);
        user.setRole(RoleEnum.CLIENTE);
        user.setId(1L);

        // Defino el comportamiento de los Mocks (Simular los llamados)
        when(userRepositoryPort.findByEmail(loginRequestDTO.getEmail())).thenReturn(Optional.of(user));

        when(passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())).thenReturn(true);

        when(tokenServicePort.generateToken(user)).thenReturn(expectedToken);

        // WHEN
        LoginResponseDTO result = loginService.login(loginRequestDTO);

        // THEN
        assertNotNull(result, "El resultado no debería ser nulo.");
        assertEquals(result.getToken(), expectedToken, "El token en el resultado debería ser " + expectedToken);


        verify(userRepositoryPort).findByEmail(testEmail);
        verify(passwordEncoder).matches(loginRequestDTO.getPassword(), user.getPassword());
        verify(tokenServicePort).generateToken(user);

    }

}
