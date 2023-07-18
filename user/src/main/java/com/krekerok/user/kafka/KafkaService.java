package com.krekerok.user.kafka;

import com.krekerok.user.dto.kafka.RegistrationMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@EnableKafka
@Component
@RequiredArgsConstructor
public class KafkaService {

    @Value("${topic.registration}")
    private String registrationTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageRegister(RegistrationMessageDto message) {
        kafkaTemplate.send(registrationTopic, message);
        log.info("{} sent successfully in the topic: {}", message.toString(), registrationTopic);
    }
}

