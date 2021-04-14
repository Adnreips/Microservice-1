package com.springboot.microservice.config;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "broker")
@Component
public class ActiveMqConfigProperties {

    private String brokerUrl;

    private String userName;

    private String password;

    private String queueName;


}
