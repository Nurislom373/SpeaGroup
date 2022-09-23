package org.khasanof.auth_service.exception.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException {
    public PasswordDoesNotMatchException() {
    }

    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
