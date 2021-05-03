package
        com.springboot.microservice.forex.controller;


import com.springboot.microservice.CurrencyConversionDto;
import com.springboot.microservice.forex.service.ExchangeValueServiceImpl;
import com.springboot.microservice.forex.service.RestTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@Api("Get exchange value")
@RestController
@RequestMapping(value = "/exchangevalue")
@Slf4j
public class ExchangeValueController {

    private final ExchangeValueServiceImpl exchangeValueServiceImpl;
    private final RestTemplateService restTemplateService;

    public ExchangeValueController(ExchangeValueServiceImpl exchangeValueServiceImpl, RestTemplateService restTemplateService) {
        this.exchangeValueServiceImpl = exchangeValueServiceImpl;
        this.restTemplateService = restTemplateService;
    }

    @ApiOperation("Get exchange value resttemplate sync")
    @PostMapping(value = "/retrieve")
    public CurrencyConversionDto retrieveAndSendExchangeValue(@RequestBody CurrencyConversionDto currencyConversionDto) {
        log.info("Получен запрос на синхронный расчет курса обмена: {}", currencyConversionDto);
        exchangeValueServiceImpl.setConversionMultiple(currencyConversionDto);
        log.info("Подготовлен ответ с currencyConversionDto: {}", currencyConversionDto);
        return currencyConversionDto;
    }

    @ApiOperation("Get exchange value resttemplate async")
    @PostMapping(value = "/retrieveasyncrequest")
    public ResponseEntity<String> retrieveAndSendExchangeValueAsync(@RequestBody CurrencyConversionDto currencyConversionDto) {
        log.info("Получен запрос на асинхронный расчет курса обмена: {}", currencyConversionDto);
        CompletableFuture.runAsync(() -> {
            exchangeValueServiceImpl.setConversionMultiple(currencyConversionDto);
            restTemplateService.sendExchangeValue(currencyConversionDto);
        });
        return ResponseEntity.ok("Асинхронный запрос обрабатывается...");
    }
}
