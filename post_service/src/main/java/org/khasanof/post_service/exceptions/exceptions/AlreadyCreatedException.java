package org.khasanof.post_service.exceptions.exceptions;

public class AlreadyCreatedException extends RuntimeException {

    public AlreadyCreatedException() {
    }

    public AlreadyCreatedException(String message) {
        super(message);
    }
}
