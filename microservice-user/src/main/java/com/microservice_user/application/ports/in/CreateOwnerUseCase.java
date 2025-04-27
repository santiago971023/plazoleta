package com.microservice_user.application.ports.in;

import com.microservice_user.application.DTOs.CreateOwnerDTO;
import com.microservice_user.domain.User;

public interface CreateOwnerUseCase {
    User createOwner(CreateOwnerDTO ownerDTO);
}
