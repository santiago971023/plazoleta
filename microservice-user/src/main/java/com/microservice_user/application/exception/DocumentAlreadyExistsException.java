package com.microservice_user.application.exception;

public class DocumentAlreadyExistsException extends RuntimeException {
    public DocumentAlreadyExistsException(String message) {
        super(message);
    }
}
