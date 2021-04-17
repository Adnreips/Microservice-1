package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.model.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import com.springboot.microservice.forex.rest.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ExchangeValueService {

    private final ExchangeValueRepository exchangeValueRepository;


    @Autowired
    public ExchangeValueService(ExchangeValueRepository exchangeValueRepository) {
        this.exchangeValueRepository = exchangeValueRepository;
    }

    public ExchangeValue getConversionMultiple(String from, String to) {

        ExchangeValue exchangeValue = exchangeValueRepository
                .findByFromAndTo(from, to);
        log.info("exchangeValue {}", exchangeValue);

        return exchangeValue;
    }

    @Async
    public CompletableFuture<ExchangeValue> getConversionMultipleAsync(String from, String to) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("InterruptedException", e);
        }

        ExchangeValue exchangeValue = exchangeValueRepository
                .findByFromAndTo(from, to);

        return CompletableFuture.completedFuture(exchangeValue);
    }

}
