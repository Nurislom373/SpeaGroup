package org.khasanof.question_service.exception.exceptions;

public class InvalidValidationException extends RuntimeException {
    public InvalidValidationException() {
    }

    public InvalidValidationException(String message) {
        super(message);
    }
}
