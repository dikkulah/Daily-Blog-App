package com.dikkulah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlingResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({TokenNotValidException.class})
    public ResponseEntity<ErrorResponse> handlingTokenNotValidException(TokenNotValidException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({AlreadyInUseException.class})
    public ResponseEntity<ErrorResponse> handlingResourceNotFoundException(AlreadyInUseException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }



}
