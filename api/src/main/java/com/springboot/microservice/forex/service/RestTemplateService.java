package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Async
@Slf4j
@Component
public class RestTemplateService {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void beginAsyncExchangeValue(CurrencyConversionDto currencyConversionDto) {
        HttpEntity<CurrencyConversionDto> requestBody = new HttpEntity<>(currencyConversionDto);
        ResponseEntity<CurrencyConversionDto> result
                = restTemplate.postForEntity("http://localhost:8100/currencyconversion/retrieveasyncresponse", requestBody, CurrencyConversionDto.class);
    }
}