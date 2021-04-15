package com.springboot.microservice.forex;


import com.springboot.microservice.forex.config.ActiveMqConfigProperties;
import com.springboot.microservice.forex.model.ExchangeValue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties(ActiveMqConfigProperties.class)
@EnableEurekaClient
@EnableAsync
@EnableSwagger2
@ConfigurationPropertiesScan("com.springboot.microservice")
public class ForexBackendApplication {

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(
                ForexBackendApplication.class, args);

    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }



//
//	@Bean
//	public Executor taskExecutor() {
//
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(2);
//		executor.setMaxPoolSize(2);
//		executor.setQueueCapacity(500);
//		executor.setWaitForTasksToCompleteOnShutdown(true);
//		executor.initialize();
//		return executor;
//	}


}