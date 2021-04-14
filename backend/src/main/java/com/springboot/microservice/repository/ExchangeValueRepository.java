package com.springboot.microservice.repository;


import com.springboot.microservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, UUID> {
    ExchangeValue findByFromAndTo(String from, String to);
}
