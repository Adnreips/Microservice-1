package com.springboot.microservice.controller;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.service.ExchangeValueService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


@Api
@RestController
@RequestMapping(value = "/exchangevalue")
public class ExchangeValueController {

    ExchangeValueService exchangeValueService;

    @GetMapping
    public CurrencyConversionDto retrieveExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto){

        exchangeValueService.setConversionMultiple(currencyConversionDto);

        return currencyConversionDto;
    }



}
