package com.springboot.microservice.forex.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.CurrencyConversionDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
@Data
public class MessageListener {

    @Value("${se.jms.queue.object}")
    private String queueNameObject;

    private final Sender sender;

    @Autowired
    public MessageListener(Sender sender) {
        this.sender = sender;
    }

    @JmsListener(destination = "${me.jms.queue.object}")
    public void receiveMessage(CurrencyConversionDto message) throws JMSException, JsonProcessingException {

        sender.sendMessageObject(queueNameObject, message);

    }


}