package com.example.demo.util.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateDataException.class)
    public final ResponseEntity<ErrorMassage> duplicatedData(Exception ex, WebRequest request) {
        ErrorMassage exceptionResponse = new ErrorMassage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ErrorMassage> dataNotFound(Exception ex, WebRequest request) {
        ErrorMassage exceptionResponse = new ErrorMassage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}