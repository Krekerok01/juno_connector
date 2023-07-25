package com.krekerok.notification.consumer.user;

import com.krekerok.notification.consumer.KafkaConsumer;
import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.message.MessageType;
import com.krekerok.notification.dto.payload.user.UserGreetingPayload;

import com.krekerok.notification.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationConsumer implements KafkaConsumer<UserGreetingPayload> {

    private final EmailSenderService emailSenderService;

    @Override
    @KafkaListener(topics = "${spring.kafka.topics.user.registration}")
    public void consume(BaseMessage<UserGreetingPayload> message)  {
        message.setMessageType(MessageType.GREETING);
        String text = "text";
        emailSenderService.sendMessage(message.getEmail(), "Hello", text);
    }
}