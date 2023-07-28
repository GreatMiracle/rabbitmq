package com.rabbitmq.consumer.consumer;

import com.rabbitmq.library.message.Picture;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MyPictureImageDLXConsumer {
    @RabbitListener(queues = "q.mypicture.image")
    public void listen(String message){
        var p = JsonUtils.convertJsonToObject(message, Picture.class);

        if (p.getSize() > 9000) {
            throw new AmqpRejectAndDontRequeueException("Picture size too large : " + p);
        }

        log.info("On image : {}", p.toString());
    }

}
