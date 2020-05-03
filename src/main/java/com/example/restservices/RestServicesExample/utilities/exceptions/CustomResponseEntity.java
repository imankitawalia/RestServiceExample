package com.example.restservices.RestServicesExample.utilities.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice //to be applied to all the controllers + share data between multiple controllers
@RestController // provides response back in case of exception
public class CustomResponseEntity extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // call this method in case of all kinds of exception
    public final ResponseEntity<Object> customExceptionHandler(Exception ex, WebRequest request) {

        ResponseException responseException = new ResponseException(ex.getMessage(),request.getDescription(false),new Date());
        return new ResponseEntity(responseException, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UserNotFoundException.class) // call this method in case of only UserNotFoundException
    public final ResponseEntity<Object> userNotFoundExceptionExceptionHandler(Exception ex, WebRequest request) {

        ResponseException responseException = new ResponseException(ex.getMessage(),request.getDescription(false),new Date());
        return new ResponseEntity(responseException, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //ResponseException responseException = new ResponseException("Validation fail",ex.getBindingResult().toString(),new Date());
        ResponseException responseException = new ResponseException("Validation fail",ex.getMessage(),new Date());
        return new ResponseEntity(responseException, HttpStatus.NOT_FOUND);
    }
}
