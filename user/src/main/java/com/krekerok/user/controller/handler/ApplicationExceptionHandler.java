package com.krekerok.user.controller.handler;

import com.krekerok.user.dto.response.ExceptionResponse;
import com.krekerok.user.exception.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionResponse> entityExistsExceptionHandler(EntityExistsException e) {
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
