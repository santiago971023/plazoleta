package com.microservice_user.infrastructure.adapters.in.web.advice;

import com.microservice_user.application.exception.DocumentAlreadyExistsException;
import com.microservice_user.application.exception.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DocumentAlreadyExistsException.class)
    public ResponseEntity<String> handleDocumentAlreadyExists(DocumentAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }


}
