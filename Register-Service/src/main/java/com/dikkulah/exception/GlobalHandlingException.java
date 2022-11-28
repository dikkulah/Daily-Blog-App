package com.dikkulah.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalHandlingException {

    @ExceptionHandler({EmailNotUniqueException.class})
    public ResponseEntity<ErrorResponse> handlingNotFoundException(EmailNotUniqueException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WrongEmailOrPasswordException.class})
    public ResponseEntity<ErrorResponse> handlingWrongEmailOrPasswordException(WrongEmailOrPasswordException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlingResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<ErrorResponse> handlingResourceNotFoundException(SignatureException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<ErrorResponse> handlingResourceNotFoundException(ExpiredJwtException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DailyApiException.class})
    public ResponseEntity<ErrorResponse> handlingDailyApiException(DailyApiException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, new Date()), HttpStatus.BAD_REQUEST);
    }

}
