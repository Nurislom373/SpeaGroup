package org.khasanof.post_service.exceptions.exceptions;

public class ListIsNullException extends RuntimeException {
    public ListIsNullException() {
    }

    public ListIsNullException(String message) {
        super(message);
    }
}
