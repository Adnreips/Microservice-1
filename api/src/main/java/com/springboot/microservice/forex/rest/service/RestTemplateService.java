package com.springboot.microservice.forex.rest.service;

import com.springboot.microservice.CurrencyConversionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Async
@Slf4j
@Component
public class RestTemplateService {

    public void beginAsyncExchangeValue(CurrencyConversionDto currencyConversionDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CurrencyConversionDto> requestBody = new HttpEntity<>(currencyConversionDto);
        ResponseEntity<CurrencyConversionDto> result
                = restTemplate.postForEntity("http://localhost:8100/currencyconversion/retrieveasyncresponse", requestBody, CurrencyConversionDto.class);
    }
}