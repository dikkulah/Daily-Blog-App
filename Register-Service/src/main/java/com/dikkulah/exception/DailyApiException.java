package com.dikkulah.exception;

public class DailyApiException extends RuntimeException{

    public DailyApiException(String email) {
        super(email);
    }
}