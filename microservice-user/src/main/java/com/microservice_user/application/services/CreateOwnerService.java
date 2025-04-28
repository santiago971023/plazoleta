package com.microservice_user.application.services;

import com.microservice_user.application.DTOs.CreateOwnerDTO;
import com.microservice_user.application.exception.DocumentAlreadyExistsException;
import com.microservice_user.application.exception.EmailAlreadyExistsException;
import com.microservice_user.application.exception.ErrorMessagesApplication;
import com.microservice_user.application.ports.in.CreateOwnerUseCase;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateOwnerService implements CreateOwnerUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public CreateOwnerService(UserRepositoryPort userRepositoryPort, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createOwner(CreateOwnerDTO ownerDTO) {

        if(userRepositoryPort.existsByDocumentNumber(ownerDTO.getDocumentNumber())){
            throw new DocumentAlreadyExistsException(ErrorMessagesApplication.DOCUMENT_NUMBER_ALREADY_EXISTS.getMessage());
        }
        if (userRepositoryPort.existsByEmail(ownerDTO.getEmail())){
            throw new EmailAlreadyExistsException(ErrorMessagesApplication.EMAIL_ALREADY_EXISTS.getMessage());
        }

        User userToCreate = userMapper.toUser(ownerDTO);
        userToCreate.setRole(RoleEnum.PROPIETARIO);
        userToCreate.setPassword(passwordEncoder.encode(ownerDTO.getPassword()));

        return userRepositoryPort.save(userToCreate);

    }
}
