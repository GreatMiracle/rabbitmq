package com.rabbitmq.consumer.consumer;

import com.rabbitmq.library.message.Picture;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PictureConsumer {

    @RabbitListener(queues = "q.picture.filter")
    public void listenPictureFilterConsumer(String message) {
        var p = JsonUtils.convertJsonToObject(message, Picture.class);
        log.info("On filter : {}", p.toString());
    }

    @RabbitListener(queues = "q.picture.image")
    public void listenPictureImageConsumer(String message){
        var p = JsonUtils.convertJsonToObject(message, Picture.class);
        log.info("On image : {}", p.toString());
    }


    @RabbitListener(queues = "${spring.rabbitmq.queue.picture.log}")
    public void listenPictureLogConsumer(String message) {
        var p = JsonUtils.convertJsonToObject(message, Picture.class);
        log.info("On log : {}", p.toString());
    }

    @RabbitListener(queues = "q.picture.vector")
    public void listen(String message) {
        var p = JsonUtils.convertJsonToObject(message, Picture.class);
        log.info("On vector : {}", p.toString());
    }

}
