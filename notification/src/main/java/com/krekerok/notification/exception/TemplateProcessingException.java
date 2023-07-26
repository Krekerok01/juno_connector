package com.krekerok.notification.exception;

public class TemplateProcessingException extends RuntimeException{

    public TemplateProcessingException() {
        super();
    }

    public TemplateProcessingException(String message) {
        super(message);
    }

    public TemplateProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateProcessingException(Throwable cause) {
        super(cause);
    }
}
