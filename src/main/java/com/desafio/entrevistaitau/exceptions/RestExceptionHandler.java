package com.desafio.entrevistaitau.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> resourceNotFoundException(ResourceNotFoundException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = new Error(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DuplicateCarException.class)
    public ResponseEntity<Error> duplicateCarException(DuplicateCarException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = new Error(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(error);
    }

}
