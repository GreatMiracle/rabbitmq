package com.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ExchangeConfig {

    @Bean
    public FanoutExchange fanoutExchangeHr() {
        return new FanoutExchange("x.hr");
    }

    @Bean
    public DirectExchange directExchangePicture() {
        return new DirectExchange("x.picture");
    }

    @Bean
    public TopicExchange topicExchangePicture() {
        return new TopicExchange("x.picture2");
    }
}
