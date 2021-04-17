package com.springboot.microservice.forex.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.model.ExchangeValue;
import com.springboot.microservice.forex.service.ExchangeValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class MessageListener {


    @Value("${se.jms.queue.object}")
    private String queueNameObject;

    private Sender sender;

    private ExchangeValueService exchangeValueService;

    private Environment environment;


    public MessageListener(Sender sender, ExchangeValueService exchangeValueService,
                           Environment environment) {
        this.sender = sender;
        this.exchangeValueService = exchangeValueService;
        this.environment = environment;
    }

    @JmsListener(destination = "${me.jms.queue.object}")
    public void receiveMessage(CurrencyConversionDto message) throws JMSException, JsonProcessingException {

        log.info("Port {}", environment.getProperty("local.server.port"));

        ExchangeValue exchangeValue = exchangeValueService.getConversionMultiple(message.getFrom(), message.getTo());

        message.setConversionMultiple(exchangeValue.getConversionMultiple());

        log.info("MessageListener {}", message);

        sender.sendMessageObject(queueNameObject, message);

    }


}