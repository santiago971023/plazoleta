package com.microservice_user.infrastructure.exception;

public class UserNotFoundByEmailInfra extends RuntimeException {
    public UserNotFoundByEmailInfra(String message) {
        super(message);
    }
}
