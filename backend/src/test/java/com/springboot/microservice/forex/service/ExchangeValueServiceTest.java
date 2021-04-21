package com.springboot.microservice.forex.service;

import com.springboot.microservice.forex.entity.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ExchangeValueServiceTest {

    ExchangeValueRepository exchangeValueRepository;
    ExchangeValueService exchangeValueService;

    @BeforeEach
    public void setUp() {
        exchangeValueRepository = Mockito.mock(ExchangeValueRepository.class);
        exchangeValueService = new ExchangeValueService(exchangeValueRepository);
    }

    @DisplayName("Test method getConversionMultiple")
    @Test
    void getConversionMultipleTest() {

        String from = "USD";
        String to = "INR";
        ExchangeValue expectedExchangeValue = new ExchangeValue(1L, new BigDecimal("65.00"), from, 1, to);
        when(exchangeValueRepository.findByFromAndTo("USD", "INR")).thenReturn(expectedExchangeValue);
        ExchangeValue actualConversionMultiple = exchangeValueService.getConversionMultiple(from, to);
        assertEquals(expectedExchangeValue, actualConversionMultiple);
    }

    @DisplayName("Test method getConversionMultipleAsync")
    @Test
    void getConversionMultipleAsyncTest() {
        String from = "USD";
        String to = "INR";
        ExchangeValue expectedExchangeValue = new ExchangeValue(1L, new BigDecimal("65.00"), from, 1, to);
        when(exchangeValueRepository.findByFromAndTo("USD", "INR")).thenReturn(expectedExchangeValue);
        ExchangeValue actualConversionMultiple = exchangeValueService.getConversionMultiple(from, to);
        assertEquals(expectedExchangeValue, actualConversionMultiple);


    }
}