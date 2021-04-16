package
        com.springboot.microservice.forex.controller;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.model.ExchangeValue;
import com.springboot.microservice.forex.rest.service.RestTemplateService;
import com.springboot.microservice.forex.service.ExchangeValueService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Api
@RestController
@RequestMapping(value = "/exchangevalue")
public class ExchangeValueController {

    ExchangeValueService exchangeValueService;
    RestTemplateService restTemplateService;

    public ExchangeValueController(ExchangeValueService exchangeValueService, RestTemplateService restTemplateService) {
        this.exchangeValueService = exchangeValueService;
        this.restTemplateService = restTemplateService;
    }

    @PostMapping(value = "/retrieve", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CurrencyConversionDto retrieveAndSendExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto) {

        ExchangeValue exchangeValue = exchangeValueService.getConversionMultiple(currencyConversionDto.getFrom(), currencyConversionDto.getTo());
        currencyConversionDto.setConversionMultiple(exchangeValue.getConversionMultiple());

        return currencyConversionDto;
    }

    @PostMapping(value = "/retrieveasyncrequest", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void retrieveAndSendAsyncExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto) {

        CompletableFuture<ExchangeValue> currencyConversionDtoCompletableFuture = exchangeValueService
                .getConversionMultipleAsync(currencyConversionDto.getFrom(), currencyConversionDto.getTo());

        try {
            currencyConversionDto.setConversionMultiple(currencyConversionDtoCompletableFuture.get().getConversionMultiple());
            restTemplateService.beginAsyncExchangeValue(currencyConversionDto);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<CurrencyConversionDto> requestBody = null;
//        try {
//            requestBody = new HttpEntity<>(currencyConversionDtoCompletableFuture.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        ResponseEntity<CurrencyConversionDto> result
//                = restTemplate.postForEntity("http://localhost:8100/currencyconversion/retrieveasyncresponse", requestBody, CurrencyConversionDto.class);


    }


}