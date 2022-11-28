package com.dikkulah.exception;

import org.springframework.beans.factory.annotation.Value;

public class WrongEmailOrPasswordException extends RuntimeException {
    public WrongEmailOrPasswordException() {
        super("Wrong login information.");
    }
}
