package com.krekerok.notification.consumer;

import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.message.MessageType;

import com.krekerok.notification.service.EmailSenderService;
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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final EmailSenderService emailSenderService;
    private final MessageSubjectGetter messageSubjectGetter;
    private final Configuration configuration;

    @KafkaListener(topics = "${spring.kafka.topics.user.registration}")
    public void consumeUserRegistrations(BaseMessage message)  {
        message.setMessageType(MessageType.GREETING);
        try {
            String emailContent = getEmailContent(message);
            emailSenderService.sendMessage(message.getEmail(), messageSubjectGetter.getSubject(message), emailContent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    String getEmailContent(BaseMessage message) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("payload", message.getPayload());
        Locale locale = message.getLocalization();
        configuration
            .getTemplate("user/registration/userRegistrationTemplate.ftl", locale).process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}