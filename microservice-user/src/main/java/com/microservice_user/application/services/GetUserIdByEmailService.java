package com.microservice_user.application.services;

import com.microservice_user.application.ports.in.GetUserIdByEmailUseCase;
import com.microservice_user.application.ports.out.UserRepositoryPort;
import com.microservice_user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GetUserIdByEmailService implements GetUserIdByEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetUserIdByEmailService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<Long> getUserIdByEmail(String email) {

        Optional<User> userOptional = userRepositoryPort.findByEmail(email);
        return userOptional.map(User::getId);
    }
}
