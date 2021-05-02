package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import org.springframework.stereotype.Service;

@Service
public interface ExchangeValueService {

    public CurrencyConversionDto setConversionMultiple(CurrencyConversionDto currencyConversionDto);

}
