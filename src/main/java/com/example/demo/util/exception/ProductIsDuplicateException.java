package com.example.demo.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.CONFLICT)
public class ProductIsDuplicateException extends  Exception{
}
