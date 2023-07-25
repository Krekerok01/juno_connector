package com.krekerok.notification.consumer;

import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.message.MessageType;

import com.krekerok.notification.service.EmailSenderService;
import com.krekerok.notification.util.MessageSubjectGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final EmailSenderService emailSenderService;
    private final MessageSubjectGetter messageSubjectGetter;

    @KafkaListener(topics = "${spring.kafka.topics.user.registration}")
    public void consumeUserRegistrations(BaseMessage message)  {
        message.setMessageType(MessageType.GREETING);
        String text = "text";
        emailSenderService.sendMessage(message.getEmail(), messageSubjectGetter.getSubject(message), text);
    }
}