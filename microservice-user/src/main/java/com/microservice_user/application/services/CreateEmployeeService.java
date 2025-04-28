package com.microservice_user.application.services;

import com.microservice_user.application.DTOs.CreateEmployeeDTO;
import com.microservice_user.application.exception.DocumentAlreadyExistsException;
import com.microservice_user.application.exception.EmailAlreadyExistsException;
import com.microservice_user.application.exception.ErrorMessagesApplication;
import com.microservice_user.application.ports.in.CreateEmployeeUseCase;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.RoleEnum;
import com.microservice_user.domain.User;
import com.microservice_user.infrastructure.adapters.out.persistence.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements CreateEmployeeUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public CreateEmployeeService(UserRepositoryPort userRepositoryPort, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createEmployee(CreateEmployeeDTO employeeDTO) {

        if(userRepositoryPort.existsByDocumentNumber(employeeDTO.getDocumentNumber())){
            throw new DocumentAlreadyExistsException(ErrorMessagesApplication.DOCUMENT_NUMBER_ALREADY_EXISTS.getMessage());
        }
        if (userRepositoryPort.existsByEmail(employeeDTO.getEmail())){
            throw new EmailAlreadyExistsException(ErrorMessagesApplication.EMAIL_ALREADY_EXISTS.getMessage());
        }

        User userToCreate = userMapper.toUser(employeeDTO);
        userToCreate.setRole(RoleEnum.EMPLEADO);
        userToCreate.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));

        return userRepositoryPort.save(userToCreate);


    }
}



