package com.springboot.microservice.forex.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "broker")
@Component
@PropertySource("classpath:application.properties")
@Data
public class ActiveMqConfigProperties {

    private String brokerUrl;
    private String userName;
    private String password;


}
