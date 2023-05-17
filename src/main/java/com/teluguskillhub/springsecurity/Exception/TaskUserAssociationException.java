package com.teluguskillhub.springsecurity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskUserAssociationException extends RuntimeException{
    private final String message;

    public TaskUserAssociationException(String message) {
        super(message);
        this.message =message;
    }
}
