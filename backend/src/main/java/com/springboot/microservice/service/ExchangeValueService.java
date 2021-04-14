package com.springboot.microservice.service;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.model.ExchangeValue;
import com.springboot.microservice.repository.ExchangeValueRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class ExchangeValueService {

    ExchangeValueRepository exchangeValueRepository;

    @Autowired
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
