package com.microservice_user.application.services;

import com.microservice_user.application.DTOs.CreateClientDTO;
import com.microservice_user.application.exception.DocumentAlreadyExistsException;
import com.microservice_user.application.exception.EmailAlreadyExistsException;
import com.microservice_user.application.exception.ErrorMessagesApplication;
import com.microservice_user.application.ports.in.CreateClientUseCase;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService implements CreateClientUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public CreateClientService(UserRepositoryPort userRepositoryPort, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createClient(CreateClientDTO clientDto) {

        if(userRepositoryPort.existsByDocumentNumber(clientDto.getDocumentNumber())){
            throw new DocumentAlreadyExistsException(ErrorMessagesApplication.DOCUMENT_NUMBER_ALREADY_EXISTS.getMessage());
        }
        if(userRepositoryPort.existsByEmail(clientDto.getEmail())){
            throw new EmailAlreadyExistsException(ErrorMessagesApplication.EMAIL_ALREADY_EXISTS.getMessage());
        }

        User userToCreate = userMapper.toUser(clientDto);
        userToCreate.setRole(RoleEnum.CLIENTE);
        userToCreate.setPassword(passwordEncoder.encode(clientDto.getPassword()));

        return userRepositoryPort.save(userToCreate);
    }


}
