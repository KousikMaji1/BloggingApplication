package com.app.blogging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                             WebRequest request){
        ErrorDetails errorDetails;
        errorDetails = new ErrorDetails(new Date(),resourceNotFoundException.getMessage(),
                request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    //handle custom validation error

    /**
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleCustomValidationException(MethodArgumentNotValidException exception)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                "Validation Error",
                exception.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //handle global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception,
                                                             WebRequest request){
        ErrorDetails errorDetails;
        errorDetails = new ErrorDetails(new Date(),exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
