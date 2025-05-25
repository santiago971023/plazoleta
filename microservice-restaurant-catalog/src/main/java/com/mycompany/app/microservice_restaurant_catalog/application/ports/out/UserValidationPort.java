package com.mycompany.app.microservice_restaurant_catalog.application.ports.out;

public interface UserValidationPort {

    boolean isValidOwner(Long id);

}
