package com.example.demo.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {

    public DataNotFoundException(String message) {
        super(message);
    }

    public static void check(boolean expression, String message) throws DataNotFoundException {
        if (expression)
            throw new DataNotFoundException(message);
    }
}
