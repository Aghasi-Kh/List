package com.example.demo.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateDataException extends Exception {

    public DuplicateDataException(String message) {
        super(message);
    }

    public static void check(boolean expression, String message) throws DataNotFoundException {
        if (expression)
            throw new DataNotFoundException(message);
    }
}
