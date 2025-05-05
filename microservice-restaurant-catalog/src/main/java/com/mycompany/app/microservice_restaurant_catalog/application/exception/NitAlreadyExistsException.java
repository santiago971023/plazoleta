package com.mycompany.app.microservice_restaurant_catalog.application.exception;

public class NitAlreadyExistsException extends RuntimeException {
    public NitAlreadyExistsException(String message) {
        super(message);
    }
}
