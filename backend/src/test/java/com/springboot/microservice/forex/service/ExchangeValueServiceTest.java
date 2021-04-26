package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.entity.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ExchangeValueServiceTest {

    private ExchangeValueRepository exchangeValueRepository;
    private ExchangeValueServiceImpl exchangeValueServiceImpl;
    private CurrencyConversionDto currencyConversionDto;
    private ExchangeValue exchangeValue;

    @BeforeEach
    public void setUp() {
        exchangeValueRepository = Mockito.mock(ExchangeValueRepository.class);
        exchangeValueServiceImpl = new ExchangeValueServiceImpl(exchangeValueRepository);
        currencyConversionDto = new CurrencyConversionDto(1L, "EUR", "RUB", new BigDecimal("1"),
                new BigDecimal("1"), new BigDecimal("0"), 1);
        exchangeValue = new ExchangeValue(1L, new BigDecimal("85.00"), "EUR", 1, "RUB");
        when(exchangeValueRepository.findByFromAndTo("EUR", "RUB")).thenReturn(exchangeValue);

    }

    @DisplayName("Test method getConversionMultiple")
    @Test
    void setConversionMultipleTest() {
        BigDecimal expectedConversionMultiple = exchangeValue.getConversionMultiple();
        BigDecimal actualConversionMultiple = exchangeValueServiceImpl.setConversionMultiple(currencyConversionDto).getConversionMultiple();
        assertEquals(expectedConversionMultiple, actualConversionMultiple);
    }

    @DisplayName("Test method getConversionMultipleAsync")
    @Test
    void setConversionMultipleAsyncTest() {
        BigDecimal expectedConversionMultiple = exchangeValue.getConversionMultiple();
        BigDecimal actualConversionMultiple = null;
        try {
            actualConversionMultiple = exchangeValueServiceImpl.setConversionMultipleAsync(currencyConversionDto).get().getConversionMultiple();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(expectedConversionMultiple, actualConversionMultiple);
    }

}