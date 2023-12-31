package com.krekerok.profile.exception;

public class EntityExistsException extends RuntimeException{

    public EntityExistsException() {
        super();
    }

    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistsException(Throwable cause) {
        super(cause);
    }
}