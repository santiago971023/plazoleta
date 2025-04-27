package com.microservice_user.application.ports.in;

import com.microservice_user.application.DTOs.CreateClientDTO;
import com.microservice_user.domain.User;

public interface CreateClientUseCase {
    User createClient(CreateClientDTO clentDto);
}
