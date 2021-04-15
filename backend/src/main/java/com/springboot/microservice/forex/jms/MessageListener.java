package com.springboot.microservice.forex.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.service.ExchangeValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class MessageListener {

    private Sender sender;

    private String queueName;

    ExchangeValueService exchangeValueService;


    public MessageListener(Sender sender, ExchangeValueService exchangeValueService) {
        this.sender = sender;
        this.exchangeValueService = exchangeValueService;
    }

        @JmsListener(destination = "${my.jms.queue.object}")
    public void receiveMessage(CurrencyConversionDto message) throws JMSException, JsonProcessingException {
        log.info("Received message ", message);

        exchangeValueService.setConversionMultiple(message);

        sender.sendMessageObject(queueName, message);

    }


}