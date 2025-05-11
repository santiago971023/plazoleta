package com.mycompany.app.microservice_restaurant_catalog.application.exception;

public class RestaurantNameAlreadyExistsException extends RuntimeException {
    public RestaurantNameAlreadyExistsException(String message) {
        super(message);
    }
}
