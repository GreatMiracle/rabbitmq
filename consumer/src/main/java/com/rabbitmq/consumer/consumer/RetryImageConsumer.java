package com.rabbitmq.consumer.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.consumer.config.DlxProcessingErrorHandler;
import com.rabbitmq.library.message.Picture;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RetryImageConsumer {
    private static final String DEAD_EXCHANGE_NAME = "x.guideline.dead";

    private DlxProcessingErrorHandler dlxProcessingErrorHandler;

    public RetryImageConsumer() {
        this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.image.work")
    public void listener(Message message, Channel channel) {
        var p = JsonUtils.convertByteToObject(message.getBody(), Picture.class);

        try {
            if (p.getSize() > 9000) {
                throw new IOException("Size too large");
            } else {
                log.info("Creating thumbnail & publishing : " + p);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (IOException e) {
            log.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel);
        }
    }
}
