package com.krekerok.forum.controller.handler;

import com.krekerok.forum.dto.response.ExceptionResponse;
import com.krekerok.forum.exception.AccessException;
import com.krekerok.forum.exception.EntityNotFoundException;
import com.krekerok.forum.exception.ServiceClientException;
import com.krekerok.forum.exception.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessException.class)
    public ResponseEntity<ExceptionResponse> handleAccessException(AccessException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ExceptionResponse> serviceUnavailableExceptionHandler(RuntimeException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ServiceClientException.class)
    public ResponseEntity<ExceptionResponse> serviceClientExceptionHandler(RuntimeException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse buildExceptionResponse(String message, HttpStatus httpStatus) {
        return ExceptionResponse.builder()
            .message(message)
            .statusCode(httpStatus.value())
            .statusMessage(httpStatus.getReasonPhrase())
            .build();
    }
}