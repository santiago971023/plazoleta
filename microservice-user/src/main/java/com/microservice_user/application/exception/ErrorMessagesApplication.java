package com.microservice_user.application.exception;

public enum ErrorMessagesApplication {

    EMAIL_ALREADY_EXISTS("A client already exists with this email."),
    DOCUMENT_NUMBER_ALREADY_EXISTS("A client already exists with this document number."),
    USER_NOT_FOUND_WITH_EMAIL("No user found with this email."),
    PASSWORD_NOT_MATCH("This password doesn't match with the saved password in data base");

    private final String message;

    ErrorMessagesApplication(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
