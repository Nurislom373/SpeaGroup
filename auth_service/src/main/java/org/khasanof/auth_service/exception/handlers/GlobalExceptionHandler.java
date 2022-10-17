package org.khasanof.auth_service.exception.handlers;

import org.khasanof.auth_service.exception.exceptions.AlreadyCreatedException;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.exception.exceptions.PasswordDoesNotMatchException;
import org.khasanof.auth_service.response.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidValidationException.class)
    public ResponseEntity<ApplicationError> InvalidExceptionHandler(InvalidValidationException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("InvalidException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationError> NotFoundExceptionHandler(NotFoundException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("NotFoundException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(404);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyCreatedException.class)
    public ResponseEntity<ApplicationError> alreadyCreatedHandler(AlreadyCreatedException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("AlreadyCreatedException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(401);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<ApplicationError> passwordDoesNotMatchHandler(PasswordDoesNotMatchException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("PasswordDoesNotMatchException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(401);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }



}
