package com.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BindingConfig {

    private final QueueConfig queueConfig;
    private final ExchangeConfig exchangeConfig;

    @Bean
    public Binding bindingAccWithXHr() {
        return BindingBuilder.bind(queueConfig.queueHrAccounting()).to(exchangeConfig.fanoutExchangeHr());
    }

    @Bean
    public Binding bindingMarWithXHr() {
        return BindingBuilder.bind(queueConfig.queueHrMarketing()).to(exchangeConfig.fanoutExchangeHr());
    }
}
