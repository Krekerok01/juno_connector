package com.krekerok.user.kafka;

import com.krekerok.user.dto.kafka.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@EnableKafka
@Component
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, NotificationDto notificationDto) {
        kafkaTemplate.send(topic, notificationDto);
        log.info("{} sent successfully in the topic: {}", notificationDto.toString(), topic);
    }
}

