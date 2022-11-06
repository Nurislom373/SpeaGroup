package org.khasanof.auth_service.exception.handlers;

import org.khasanof.auth_service.exception.exceptions.*;
import org.khasanof.auth_service.response.ApplicationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("MethodArgumentNotValidException");
        applicationError.setMessage(ex.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListIsNullException.class)
    public ResponseEntity<ApplicationError> invalidExceptionHandler(ListIsNullException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("ListIsNullException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidValidationException.class)
    public ResponseEntity<ApplicationError> invalidExceptionHandler(InvalidValidationException exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("InvalidException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationError> notFoundExceptionHandler(NotFoundException exception, WebRequest request) {
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
        applicationError.setStatus(406);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PasswordDoesNotMatchException.class)
    public ResponseEntity<ApplicationError> passwordDoesNotMatchHandler(PasswordDoesNotMatchException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("PasswordDoesNotMatchException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientResponseException.class)
    public ResponseEntity<ApplicationError> passwordDoesNotMatchHandler(ClientResponseException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("ClientResponseException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }

}
