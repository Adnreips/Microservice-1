package com.springboot.microservice.forex.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Sender
 *
 * @author "Andrei Prokofiev"
 */

@Slf4j
@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    @Value("${spring.application.name}")
    private String nameOfApplication;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessageObject(final String queueName, final Object message) {
        log.info("Sending message {} to queue - {}", message, queueName);
        jmsTemplate.setTimeToLive(30000);
        jmsTemplate.convertAndSend(queueName, message, m -> {
                    m.setStringProperty("nameOfApplication", nameOfApplication);
                    return m;
                }

        );
    }
}
