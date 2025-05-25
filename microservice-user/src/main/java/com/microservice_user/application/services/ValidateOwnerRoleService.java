package com.microservice_user.application.services;

import com.microservice_user.application.ports.in.ValidateOwnerRoleUseCase;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateOwnerRoleService implements ValidateOwnerRoleUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public ValidateOwnerRoleService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public boolean validateRole(Long id) {

        Optional<User> userOptional = userRepositoryPort.findById(id);
        if(userOptional.isEmpty()){
            return false;
        }
        return userOptional.map(user -> user.getRole().name().equals("PROPIETARIO")).orElse(false);

    }
}
