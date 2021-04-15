package com.springboot.microservice.forex.service;

import com.springboot.microservice.forex.model.ExchangeValue;
import com.springboot.microservice.forex.repository.ExchangeValueRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class ExchangeValueServiceTest {

    @Mock
    ExchangeValueRepository exchangeValueRepository;

    @Autowired
    ExchangeValueService exchangeValueService;

    @DisplayName("Test method SetConversionMultiple")
    @Test
    void SetConversionMultipleTest() {

        when(exchangeValueRepository.findByFromAndTo("USD", "INR"))
                .thenReturn(new ExchangeValue(UUID.randomUUID(), new BigDecimal("65.00"), "USD", 1, "INR"));

        assertEquals(new BigDecimal("65.00"), exchangeValueRepository.findByFromAndTo("USD", "INR").getConversionMultiple());

    }
}