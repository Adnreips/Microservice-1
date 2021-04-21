package com.springboot.microservice.forex.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.entity.ExchangeValue;
import com.springboot.microservice.forex.service.ExchangeValueService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
@Slf4j
public class MyMessageListener extends MessageListener {

    private final ExchangeValueService exchangeValueService;

    public MyMessageListener(Sender sender, ExchangeValueService exchangeValueService) {
        super(sender);
        this.exchangeValueService = exchangeValueService;
    }

    @Override
    public void receiveMessage(CurrencyConversionDto message) throws JMSException, JsonProcessingException {

        ExchangeValue exchangeValue = exchangeValueService.getConversionMultiple(message.getFrom(), message.getTo());
        message.setConversionMultiple(exchangeValue.getConversionMultiple());
        super.receiveMessage(message);


    }
}
