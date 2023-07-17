package com.krekerok.gateway.exception;

public class ServiceClientException extends RuntimeException{

    public ServiceClientException() {
        super();
    }

    public ServiceClientException(String message) {
        super(message);
    }

    public ServiceClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceClientException(Throwable cause) {
        super(cause);
    }
}
