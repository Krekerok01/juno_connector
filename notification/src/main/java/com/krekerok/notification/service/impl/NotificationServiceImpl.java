package com.krekerok.notification.service.impl;

import com.krekerok.notification.dto.message.BaseMessage;
import com.krekerok.notification.dto.message.MessageType;
import com.krekerok.notification.exception.TemplateProcessingException;
import com.krekerok.notification.service.EmailSenderService;
import com.krekerok.notification.service.NotificationService;
import com.krekerok.notification.util.MessageSubjectGetter;
import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
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
    private final ExecutorService executorService;

    @Override
    public void processMessageSending(BaseMessage message) {
        String emailContent = getEmailContent(message);
        Failsafe.with(getRetryPolicy())
            .with(executorService)
            .onFailure((ex) -> log.info("Email sending error"))
            .runAsync(() -> emailSenderService.sendMessage(message.getEmail(), messageSubjectGetter.getSubject(message), emailContent));
    }

    private RetryPolicy<Object> getRetryPolicy() {
        return RetryPolicy.builder()
            .handle(Exception.class)
            .withDelay(Duration.ofSeconds(10))
            .withMaxRetries(3)
            .onRetry(ex -> log.info("Retry: " + ex.getLastException()))
            .onFailure(ex -> log.info("Failed: " + ex.getException()))
            .build();
    }

    private String getEmailContent(BaseMessage message) {
        try {
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> model = new HashMap<>();
            model.put("payload", message.getPayload());
            Locale locale = message.getLocalization();
            configuration.getTemplate(getFilePath(message.getMessageType()), locale).process(model, stringWriter);
            return stringWriter.getBuffer().toString();
        } catch (TemplateException | IOException e) {
            throw new TemplateProcessingException("Error while processing email content", e);
        }
    }

    private String getFilePath(MessageType messageType) {
        if (messageType.equals(MessageType.GREETING))
            return "user/registration/userRegistrationTemplate.ftl";
        return "";
    }
}