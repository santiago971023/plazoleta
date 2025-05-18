package com.mycompany.app.microservice_restaurant_catalog.application.exception;

public class OwnerNotFoundOrInvalidRoleException extends RuntimeException {
    public OwnerNotFoundOrInvalidRoleException(String message) {
        super(message);
    }
}
