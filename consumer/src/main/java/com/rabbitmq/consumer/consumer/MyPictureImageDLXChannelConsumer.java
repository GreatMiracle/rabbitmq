package com.rabbitmq.consumer.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.library.message.Picture;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class MyPictureImageDLXChannelConsumer {
    @RabbitListener(queues = "q.mypicture.image")
    public void listen(Message message, Channel channel) {
        var p = JsonUtils.convertByteToObject(message.getBody(), Picture.class);


        try {
            if (p.getSize() > 9000) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }else {
                log.info("On image DLX : {}", p.toString());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
