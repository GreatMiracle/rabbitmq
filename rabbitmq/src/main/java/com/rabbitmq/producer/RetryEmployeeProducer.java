package com.rabbitmq.producer;

import com.rabbitmq.library.message.Employee;
import com.rabbitmq.library.message.Picture;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RetryEmployeeProducer {
    RabbitTemplate rabbitTemplate;

    public void sendMessage(Employee p) {
        var json = JsonUtils.convertObjectToJson(p);
        rabbitTemplate.convertAndSend("x.guideline2.work","", json);
    }
}
