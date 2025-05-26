package com.microservice_user.application.ports.in;

import java.util.Optional;

public interface GetUserIdByEmailUseCase {
    Optional<Long> getUserIdByEmail(String email);
}
