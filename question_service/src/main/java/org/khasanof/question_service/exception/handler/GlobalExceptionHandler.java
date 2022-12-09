package org.khasanof.question_service.exception.handler;

import org.khasanof.question_service.exception.exceptions.*;
import org.khasanof.question_service.response.ApplicationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/7/2022
 * <br/>
 * Time: 10:10 PM
 * <br/>
 * <br/>
 * GlobalExceptionHandler is used to catch program runtime exception and send it as Response.
 *
 */
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
    public ResponseEntity<ApplicationError> listIsNullExceptionHandler(ListIsNullException exception, WebRequest webRequest) {
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

    @ExceptionHandler(AlreadyCreatedException.class)
    public ResponseEntity<ApplicationError> alreadyCreatedHandler(AlreadyCreatedException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("AlreadyCreatedException");
        applicationError.setMessage(exception.getMessage());
        applicationError.setStatus(406);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ClientResponseException.class)
    public ResponseEntity<ApplicationError> clientResponseExceptionHandler(ClientResponseException exception, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("ClientResponseException");
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



}
