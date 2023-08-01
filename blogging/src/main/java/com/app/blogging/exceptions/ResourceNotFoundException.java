package com.app.blogging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public static final long SerialVersionUID;

    static {
        SerialVersionUID = 1;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
