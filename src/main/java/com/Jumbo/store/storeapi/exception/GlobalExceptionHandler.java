package com.Jumbo.store.storeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleException(final Exception exception) {

        return new ResponseEntity<>("Invalid Parameter: "+exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadException(final Exception exception) {
        return new ResponseEntity<>("Invalid Request: "+exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleNotException(final Exception exception) {
        return new ResponseEntity<>("DataNotFound : "+exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}