package com.springboot.microservice.forex.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Session;
import java.util.Arrays;

/**
 * ActiveMqConfig
 *
 * @author "Andrei Prokofiev"
 */


@Configuration
@EnableJms
@Slf4j
@Data
public class ActiveMqConfig {


    @Value("${mb.activemq.url}")
    private String brokerUrl;

    @Value("${mb.activemq.username}")
    private String userName;

    @Value("${mb.activemq.password}")
    private String password;


    private  ConnectionFactory connectionFactory;

    @Autowired
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        log.info("init ConnectionFactory");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setTrustedPackages(Arrays.asList("com.springboot"));

        return connectionFactory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        log.info("init MessageConverter");
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
//        converter.setTargetType(MessageType.OBJECT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setConcurrency("1-1");
        return factory;
    }
    @Bean
    public JmsTransactionManager jmsTransactionManager() {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory());
        return jmsTransactionManager;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setSessionTransacted(true);
        template.setConnectionFactory(connectionFactory());
        template.afterPropertiesSet();
        return template;
    }



}
