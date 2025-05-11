package com.mycompany.app.microservice_restaurant_catalog.infrastructure.adapters.in.web.advice;

import com.mycompany.app.microservice_restaurant_catalog.application.exception.NitAlreadyExistsException;
import com.mycompany.app.microservice_restaurant_catalog.application.exception.RestaurantNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerAdvisor {

    // Manejador para errores de validacion del DTO (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class) // Atrapa esta excepcion
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Crea un mapa para almacenar los errores de validacion (campo: mensaje)
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // Nombre del campo que fallo
            String errorMessage = error.getDefaultMessage(); // Mensaje de la validacion (el que pusiste en las anotaciones)
            errors.put(fieldName, errorMessage); // Añade al mapa
        });
        // Devuelve una respuesta 400 Bad Request con el mapa de errores
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    @ExceptionHandler(NitAlreadyExistsException.class)
    public ResponseEntity<String> handleNitAlreadyExists(NitAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }

    @ExceptionHandler(RestaurantNameAlreadyExistsException.class)
    public ResponseEntity<String> handleRestaurantNameAlreadyExists(RestaurantNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }

}
