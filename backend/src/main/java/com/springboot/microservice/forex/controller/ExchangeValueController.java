package
        com.springboot.microservice.forex.controller;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.rest.service.RestTemplateService;
import com.springboot.microservice.forex.service.ExchangeValueServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Api("Get exchange value")
@RestController
@RequestMapping(value = "/exchangevalue")
@Slf4j
public class ExchangeValueController {

    ExchangeValueServiceImpl exchangeValueServiceImpl;
    RestTemplateService restTemplateService;

    public ExchangeValueController(ExchangeValueServiceImpl exchangeValueServiceImpl, RestTemplateService restTemplateService) {
        this.exchangeValueServiceImpl = exchangeValueServiceImpl;
        this.restTemplateService = restTemplateService;
    }

    @ApiOperation("Get exchange value resttemplate sync")
    @PostMapping(value = "/retrieve", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CurrencyConversionDto retrieveAndSendExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto) {
        exchangeValueServiceImpl.setConversionMultiple(currencyConversionDto);
        return currencyConversionDto;
    }

    @ApiOperation("Get exchange value resttemplate async")
    @PostMapping(value = "/retrieveasyncrequest", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void retrieveAndSendAsyncExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto) {

        CompletableFuture<CurrencyConversionDto> currencyConversionDtoCompletableFuture = exchangeValueServiceImpl
                .setConversionMultipleAsync(currencyConversionDto);
        try {
            CurrencyConversionDto currencyConversionDto1 = currencyConversionDtoCompletableFuture.get();
            restTemplateService.beginAsyncExchangeValue(currencyConversionDto1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
