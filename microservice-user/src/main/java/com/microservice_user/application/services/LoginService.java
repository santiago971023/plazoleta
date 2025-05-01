package com.microservice_user.application.services;

import com.microservice_user.application.DTOs.LoginRequestDTO;
import com.microservice_user.application.DTOs.LoginResponseDTO;
import com.microservice_user.application.exception.ErrorMessagesApplication;
import com.microservice_user.application.exception.PasswordDoesNotMatchException;
import com.microservice_user.application.exception.UserNotFoundByEmailException;
import com.microservice_user.application.ports.in.LoginUseCase;
import com.microservice_user.application.ports.out.TokenServicePort;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenServicePort tokenServicePort;

    public LoginService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder, TokenServicePort tokenServicePort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userRepositoryPort.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow( () -> new UserNotFoundByEmailException(ErrorMessagesApplication.USER_NOT_FOUND_WITH_EMAIL.getMessage()) );


        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new PasswordDoesNotMatchException(ErrorMessagesApplication.PASSWORD_NOT_MATCH.getMessage());
        }

        String token = tokenServicePort.generateToken(user);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);


        return loginResponseDTO;
    }

}
