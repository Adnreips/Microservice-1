package com.springboot.microservice.forex.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.service.ExchangeValueService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Slf4j
@Data
@Service
public class MessageListener {

    @Value("${se.jms.queue.object}")
    private String queueNameObject;
    private final Sender sender;
    private final ExchangeValueService exchangeValueService;

    @Autowired
    public MessageListener(Sender sender, ExchangeValueService exchangeValueService) {
        this.sender = sender;
        this.exchangeValueService = exchangeValueService;
    }

    @JmsListener(destination = "${me.jms.queue.object}")
    public void receiveMessage(CurrencyConversionDto message, MessageHeaders messageHeaders) throws JMSException, JsonProcessingException {
        exchangeValueService.setConversionMultiple(message);
        log.info("Received nameOfApplication: {}", messageHeaders.get("nameOfApplication"));
        sender.sendMessageObject(queueNameObject, message);

    }


}