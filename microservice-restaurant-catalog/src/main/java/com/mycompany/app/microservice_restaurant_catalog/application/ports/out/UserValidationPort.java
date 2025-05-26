package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

import java.util.Optional;

public interface UserValidationPort {

    boolean isValidOwner(Long id);
    Optional<Long> getUserIdByEmail(String email);

}
