package com.krekerok.notification.dto.message;

import lombok.Getter;

public enum MessageType {
    GREETING("Greeting", "Приветствие");

    @Getter
    private final String enSubject;

    @Getter
    private final String ruSubject;

    MessageType(String enSubject, String ruSubject) {
        this.enSubject = enSubject;
        this.ruSubject = ruSubject;
    }
}
