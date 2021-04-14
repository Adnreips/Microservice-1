package com.springboot.microservice.jms.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.CurrencyConversionDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class MessageListener {

    private final Sender sender;

    private String queueName;

    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    public MessageListener(Sender sender) {
        this.sender = sender;
    }

//    @JmsListener(destination = "${my.jms.queue.object}")
    public void receiveMessage(CurrencyConversionDto message) throws JMSException, JsonProcessingException {
        log.info("Received message ", message);
        sender.sendMessageObject(queueName, message);

    }


}