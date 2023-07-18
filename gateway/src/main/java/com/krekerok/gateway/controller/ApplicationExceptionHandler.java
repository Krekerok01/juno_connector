package com.krekerok.gateway.controller;

import com.krekerok.gateway.dto.ExceptionResponse;
import com.krekerok.gateway.exception.ServiceClientException;
import com.krekerok.gateway.exception.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
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