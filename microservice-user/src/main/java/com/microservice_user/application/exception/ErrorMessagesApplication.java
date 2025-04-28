package com.microservice_user.application.exception;

public enum ErrorMessagesApplication {

    EMAIL_ALREADY_EXISTS("A client already exists with this email."),
    DOCUMENT_NUMBER_ALREADY_EXISTS("A client already exists with this document number.");

    private final String message;

    ErrorMessagesApplication(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
