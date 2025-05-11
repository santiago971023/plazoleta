package com.mycompany.app.microservice_restaurant_catalog.application.exception;

public enum ErrorMessagesApplication {


    NIT_ALREADY_EXIST("The 'nit' field must be unique."),
    NAME_ALREADY_EXIST("We already have a restaurant with that 'name'.");

    private final String message;

    ErrorMessagesApplication(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
