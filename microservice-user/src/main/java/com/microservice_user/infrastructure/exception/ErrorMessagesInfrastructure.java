package com.microservice_user.infrastructure.exception;

public enum ErrorMessagesInfrastructure {

    USER_NOT_FOUND_WITH_EMAIL("No user found with this email (infra).");

    private final String message;

    ErrorMessagesInfrastructure(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
