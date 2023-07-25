package com.krekerok.notification.service.impl;

import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.message.MessageType;
import com.krekerok.notification.service.EmailSenderService;
import com.krekerok.notification.service.NotificationService;
import com.krekerok.notification.util.MessageSubjectGetter;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final EmailSenderService emailSenderService;
    private final MessageSubjectGetter messageSubjectGetter;
    private final Configuration configuration;


    @Override
    public void processMessageSending(BaseMessage message) {
        String text = getEmailContent(message);
        emailSenderService.sendMessage(message.getEmail(), messageSubjectGetter.getSubject(message), text);
    }

    // изменить обработку ошибок
    private String getEmailContent(BaseMessage message) {
        try {
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> model = new HashMap<>();
            model.put("payload", message.getPayload());
            Locale locale = message.getLocalization();
            configuration.getTemplate(getFilePath(message.getMessageType()), locale).process(model, stringWriter);
            return stringWriter.getBuffer().toString();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException("Exception");
        }
    }

    private String getFilePath(MessageType messageType) {
        if (messageType.equals(MessageType.GREETING))
            return "user/registration/userRegistrationTemplate.ftl";
        return "";
    }
}