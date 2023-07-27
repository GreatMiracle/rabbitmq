package com.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ExchangeConfig {

    @Bean
    public FanoutExchange fanoutExchangeHr() {
        return new FanoutExchange("x.hr");
    }
}
