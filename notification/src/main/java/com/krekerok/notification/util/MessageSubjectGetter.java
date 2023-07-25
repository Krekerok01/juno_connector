package com.krekerok.notification.util;

import com.krekerok.notification.dto.message.BaseMessage;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class MessageSubjectGetter {

    public String getSubject(BaseMessage message) {
        String subject = message.getMessageType().getRuSubject();
        if (message.getLocalization().equals(Locale.ENGLISH)) {
            subject = message.getMessageType().getEnSubject();
        }

        return subject;
    }
}