package com.Kyndle.exception;

public class InvalidUserProfileException extends RuntimeException {
    public InvalidUserProfileException(String message) {
        super(message);
    }
}
