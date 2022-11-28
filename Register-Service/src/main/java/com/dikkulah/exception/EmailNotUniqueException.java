package com.dikkulah.exception;

import org.springframework.beans.factory.annotation.Value;

public class EmailNotUniqueException extends RuntimeException{

    public EmailNotUniqueException(String email) {
        super(email.concat(" is not unique."));
    }
}
