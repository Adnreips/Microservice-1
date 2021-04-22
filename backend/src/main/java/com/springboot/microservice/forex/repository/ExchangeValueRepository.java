package com.springboot.microservice.forex.repository;


import com.springboot.microservice.forex.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, UUID> {

    ExchangeValue findByFromAndTo(String from, String to);

}
