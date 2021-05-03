package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;

public interface ExchangeValueService {
    CurrencyConversionDto setConversionMultiple(CurrencyConversionDto currencyConversionDto);
}
