package org.khasanof.post_service.exceptions.exceptions;

public class ClientResponseException extends RuntimeException {
    public ClientResponseException() {
    }

    public ClientResponseException(String message) {
        super(message);
    }
}
