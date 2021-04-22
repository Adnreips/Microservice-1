package com.springboot.microservice.forex.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeValueDto {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private int port;
}
