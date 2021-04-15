package com.springboot.microservice.forex.controller;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.service.ExchangeValueService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api
@RestController
@RequestMapping(value = "/exchangevalue")
public class ExchangeValueController {

    ExchangeValueService exchangeValueService;

    public ExchangeValueController(ExchangeValueService exchangeValueService) {
        this.exchangeValueService = exchangeValueService;
    }

    @PostMapping(value = "/retrieve", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CurrencyConversionDto retrieveAndSendExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto){

        exchangeValueService.setConversionMultiple(currencyConversionDto);

        return currencyConversionDto;
    }



}
