package com.rabbitmq.consumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloRabbitConsumer {
    @RabbitListener(queues = "my-student")
    public void listen(String message) {
        System.out.println("Consuming " + message);
    }
}
