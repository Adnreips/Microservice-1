package com.springboot.microservice.forex.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "broker")
public class ActiveMqConfigProperties {

    private String brokerUrl;

    private String userName;

    private String password;



}