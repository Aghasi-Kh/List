package com.example.demo.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) {
        super(message);
    }
    public static void check(boolean expression,String message) throws DuplicateDataException {
        if (expression)
            throw new DuplicateDataException(message);
    }
}
