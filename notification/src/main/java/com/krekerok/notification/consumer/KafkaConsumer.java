package com.krekerok.notification.consumer;

import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.message.MessageType;
import com.krekerok.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "${spring.kafka.topics.user.registration}")
    public void consumeUserRegistrations(BaseMessage message)  {
        message.setMessageType(MessageType.GREETING);
        notificationService.processMessageSending(message);
    }
}