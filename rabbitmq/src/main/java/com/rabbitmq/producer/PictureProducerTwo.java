package com.rabbitmq.producer;

import com.rabbitmq.library.message.Picture;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PictureProducerTwo {
    RabbitTemplate rabbitTemplate;
    Exchange topicExchangePicture;

//    {"name":"Picture 7","type":"png","source":"web","size":1946}
//    {"name":"Picture 1","type":"png","source":"web","size":8766}
    public void sendMessage(Picture p) {

        var routingkey = new StringBuilder();

        routingkey.append(p.getSource());
        routingkey.append(".");
        routingkey.append(p.getSize() > 4000 ? "large" : "small");
        routingkey.append(".");
        routingkey.append(p.getType());

        var json = JsonUtils.convertObjectToJson(p);
        rabbitTemplate.convertAndSend(topicExchangePicture.getName(), routingkey.toString(), json);
    }
}
