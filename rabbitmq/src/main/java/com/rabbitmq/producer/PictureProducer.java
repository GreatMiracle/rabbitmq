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
public class PictureProducer {
    RabbitTemplate rabbitTemplate;
    Exchange directExchangePicture;

    public void sendMessage(Picture p) {
        var json = JsonUtils.convertObjectToJson(p);
        rabbitTemplate.convertAndSend(directExchangePicture.getName(), p.getType(), json);
    }
}
