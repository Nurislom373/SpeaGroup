package org.khasanof.post_service.exceptions.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException {
    public PasswordDoesNotMatchException() {
    }

    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
