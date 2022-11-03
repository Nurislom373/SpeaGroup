package org.khasanof.auth_service.exception.exceptions;

public class ClientResponseException extends RuntimeException {
    public ClientResponseException() {
    }

    public ClientResponseException(String message) {
        super(message);
    }
}
