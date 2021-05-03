package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.entity.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        log.info("Начало процесса: {}", currencyConversionDto);

        doHardWork();

        ExchangeValue exchangeValue = exchangeValueRepository
                .findByFromAndTo(currencyConversionDto.getFrom(), currencyConversionDto.getTo());
        log.info("exchangeValue {}", exchangeValue);
        currencyConversionDto.setConversionMultiple(exchangeValue.getConversionMultiple());

        log.info("Завершение процесса: {}", currencyConversionDto);
        return currencyConversionDto;
    }

    private void doHardWork() {
        try {
            log.info("Do hard work...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("InterruptedException", e);
            Thread.currentThread().interrupt();
        }
    }

}
