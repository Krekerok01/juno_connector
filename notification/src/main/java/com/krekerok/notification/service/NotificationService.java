package com.krekerok.notification.service;

import com.krekerok.notification.dto.message.BaseMessage;

public interface NotificationService {

    void processMessageSending(BaseMessage message);
}