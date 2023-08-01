package com.krekerok.user.controller.handler;

import com.krekerok.user.dto.response.ExceptionResponse;
import com.krekerok.user.exception.EntityExistsException;
import com.krekerok.user.exception.EntityNotFoundException;
import com.krekerok.user.exception.InvalidCredentialsException;
import com.krekerok.user.exception.InvalidTokenException;
import com.krekerok.user.exception.VerificationException;
import java.util.List;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionResponse> entityExistsExceptionHandler(EntityExistsException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredentialsExceptionHandler(InvalidCredentialsException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidTokenException(InvalidTokenException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ExceptionResponse> handleVerificationException(VerificationException e) {
        return new ResponseEntity<>(buildExceptionResponse(e.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> parameterExceptionHandler(
        MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<String> errors = result.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();

        String errorMessage = !errors.isEmpty() ? errors.get(0) : "Argument validation failed";

        return new ResponseEntity<>(buildExceptionResponse(errorMessage, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }


    private ExceptionResponse buildExceptionResponse(String message, HttpStatus httpStatus) {
        return ExceptionResponse.builder()
            .message(message)
            .statusCode(httpStatus.value())
            .statusMessage(httpStatus.getReasonPhrase())
            .build();
    }
}
