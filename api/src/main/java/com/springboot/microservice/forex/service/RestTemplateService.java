package com.springboot.microservice.forex.service;

import com.springboot.microservice.CurrencyConversionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendExchangeValue(CurrencyConversionDto currencyConversionDto) {
        log.info("Отправляем post запрос с currencyConversionDto: {}", currencyConversionDto);
        HttpEntity<CurrencyConversionDto> requestBody = new HttpEntity<>(currencyConversionDto);
        ResponseEntity<String> result = restTemplate.postForEntity(
                "http://localhost:8100/currencyconversion/retrieveasyncresponse",
                requestBody,
                String.class);
        log.info("Успешно отправлен Post запрос с currencyConversionDto: {}, Ответ: {}", currencyConversionDto, result);
    }
}