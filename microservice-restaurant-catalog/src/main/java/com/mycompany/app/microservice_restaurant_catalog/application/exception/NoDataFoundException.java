package com.mycompany.app.microservice_restaurant_catalog.application.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
