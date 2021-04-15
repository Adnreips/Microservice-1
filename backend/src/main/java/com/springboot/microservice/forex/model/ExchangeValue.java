package com.springboot.microservice.forex.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class ExchangeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name="currency_from")
    private String from;

    @Column(name="currency_to")
    private String to;

    private BigDecimal conversionMultiple;

    @Transient
    private BigDecimal quantity;

    @Transient
    private BigDecimal totalCalculatedAmount;

    private int port;

    public ExchangeValue(Long id, BigDecimal conversionMultiple, String from, int port, String to) {
        this.id = id;
        this.conversionMultiple = conversionMultiple;
        this.from = from;
        this.port = port;
        this.to = to;
    }
}
