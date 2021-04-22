package com.springboot.microservice.forex.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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
    private final Environment environment;

    public Sender(JmsTemplate jmsTemplate, Environment environment) {
        this.jmsTemplate = jmsTemplate;
        this.environment = environment;
    }

    public void sendMessageObject(final String queueName, final Object message) {
        log.info("Sending message {} to queue - {}", message, queueName);
        jmsTemplate.setTimeToLive(30000);
        jmsTemplate.convertAndSend(queueName, message
//                , m->{
//            m.setStringProperty("Name of application", environment.getProperty("spring.application.name"));
//            return m;
//        }
        );
    }
}
