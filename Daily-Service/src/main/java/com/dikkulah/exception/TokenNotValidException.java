package com.dikkulah.exception;

public class TokenNotValidException extends RuntimeException {

    public TokenNotValidException() {
        super("Token not valid.");
    }
}
