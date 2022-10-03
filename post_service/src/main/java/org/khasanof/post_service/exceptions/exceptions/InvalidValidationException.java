package org.khasanof.post_service.exceptions.exceptions;

public class InvalidValidationException extends RuntimeException {
    public InvalidValidationException() {
    }

    public InvalidValidationException(String message) {
        super(message);
    }
}
