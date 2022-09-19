package org.khasanof.auth_service.exception.exceptions;

public class AlreadyCreatedException extends RuntimeException {

    public AlreadyCreatedException() {
    }

    public AlreadyCreatedException(String message) {
        super(message);
    }
}
