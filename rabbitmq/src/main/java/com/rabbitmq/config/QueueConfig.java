package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    public Queue myQueue() {
        return new Queue("my-queue");
    }

    @Bean
    public Queue myStudent() {
        return new Queue("my-student");
    }

    @Bean
    public Queue queueHrMarketing() {
        return new Queue("q.hr.marketing");
    }

    @Bean
    public Queue queueHrAccounting() {
        return new Queue("q.hr.accounting");
    }

    @Bean
    public Queue queuePictureImage() {
        return new Queue("q.picture.image");
    }

    @Bean
    public Queue queuePictureVector() {
        return new Queue("q.picture.vector");
    }
}
