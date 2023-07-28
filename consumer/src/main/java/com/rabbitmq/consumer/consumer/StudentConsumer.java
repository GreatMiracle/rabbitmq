package com.rabbitmq.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentConsumer {
    @RabbitListener(queues = "my-student")
    public void listen(String message) {
        log.info("Student sonsuming : {}", message);
    }
}
