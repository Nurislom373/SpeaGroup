package org.khasanof.question_service.exception.exceptions;

public class ListIsNullException extends RuntimeException {
    public ListIsNullException() {
    }

    public ListIsNullException(String message) {
        super(message);
    }
}
