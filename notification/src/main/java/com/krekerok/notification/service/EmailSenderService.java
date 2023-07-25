package com.krekerok.notification.service;

public interface EmailSenderService {

    void sendMessage(String email, String subject, String text);
}
