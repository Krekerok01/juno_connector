package com.krekerok.notification.consumer;

import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.payload.MessagePayload;

public interface KafkaConsumer<T extends MessagePayload> {
    void consume(BaseMessage<T> message);
}
