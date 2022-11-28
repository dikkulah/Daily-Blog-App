package com.dikkulah.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private Date date;


    public ErrorResponse(String message, HttpStatus httpStatus, Date date) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.date = date;
    }

}


