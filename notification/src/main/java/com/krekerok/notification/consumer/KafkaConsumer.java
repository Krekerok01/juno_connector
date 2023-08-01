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
        System.out.println("consumeUserRegistrations");
        notificationService.processMessageSending(message);
    }

    @KafkaListener(topics = "${spring.kafka.topics.user.change.password}")
    public void consumeUserPasswordChanges(BaseMessage message)  {
        message.setMessageType(MessageType.PASSWORD_CHANGE);
        System.out.println("consumeUserPasswordChanges");
        notificationService.processMessageSending(message);
    }
}