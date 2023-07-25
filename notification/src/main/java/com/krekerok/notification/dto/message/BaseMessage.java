package com.krekerok.notification.dto.message;

import com.krekerok.notification.dto.payload.MessagePayload;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BaseMessage<T extends MessagePayload> implements AbstractMessage {
    private String email;

    private MessageType messageType;

    private Locale localization;

    private T payload;
}

