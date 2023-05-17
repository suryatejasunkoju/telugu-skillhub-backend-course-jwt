package com.teluguskillhub.springsecurity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404
public class UserNotFoundException extends RuntimeException{
    private final String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
