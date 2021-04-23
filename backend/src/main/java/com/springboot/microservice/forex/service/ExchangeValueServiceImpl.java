package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.entity.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ExchangeValueServiceImpl implements ExchangeValueService {

    private final ExchangeValueRepository exchangeValueRepository;

    @Autowired
    public ExchangeValueServiceImpl(ExchangeValueRepository exchangeValueRepository) {
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @Override
    public CurrencyConversionDto setConversionMultiple(CurrencyConversionDto currencyConversionDto) {
        ExchangeValue exchangeValue = exchangeValueRepository
                .findByFromAndTo(currencyConversionDto.getFrom(), currencyConversionDto.getTo());
        log.info("exchangeValue {}", exchangeValue);
        currencyConversionDto.setConversionMultiple(exchangeValue.getConversionMultiple());
        return currencyConversionDto;
    }

    public CompletableFuture<CurrencyConversionDto> setConversionMultipleAsync(CurrencyConversionDto currencyConversionDto) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("InterruptedException", e);
        }
        ExchangeValue exchangeValue = exchangeValueRepository
                .findByFromAndTo(currencyConversionDto.getFrom(), currencyConversionDto.getTo());
        log.info("exchangeValue {}", exchangeValue);
        currencyConversionDto.setConversionMultiple(exchangeValue.getConversionMultiple());
        return CompletableFuture.completedFuture(currencyConversionDto);
    }

}
