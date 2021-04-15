package com.springboot.microservice.forex.service;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.model.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class ExchangeValueService {

    ExchangeValueRepository exchangeValueRepository;

    public ExchangeValueService(ExchangeValueRepository exchangeValueRepository) {
        this.exchangeValueRepository = exchangeValueRepository;
    }

    public CurrencyConversionDto setConversionMultiple (CurrencyConversionDto currencyConversionDto){

        ExchangeValue exchangeValue = exchangeValueRepository
                .findByFromAndTo(currencyConversionDto.getFrom(), currencyConversionDto.getTo());

        currencyConversionDto.setConversionMultiple(exchangeValue.getConversionMultiple());

        return currencyConversionDto;
    }



}
