package com.hierarchy.hierarchy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<String> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}