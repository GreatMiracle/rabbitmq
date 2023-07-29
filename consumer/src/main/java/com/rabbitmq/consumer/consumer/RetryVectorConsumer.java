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
public class RetryVectorConsumer {
    private static final String DEAD_EXCHANGE_NAME = "x.guideline.dead";

    private DlxProcessingErrorHandler dlxProcessingErrorHandler;


    public RetryVectorConsumer() {
        this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.vector.work")
    public void listen(Message message, Channel channel)
            throws InterruptedException,IOException {
        try {
            var p = JsonUtils.convertByteToObject(message.getBody(), Picture.class);
            // process the image
            if (p.getSize() > 9000) {
                // throw exception, we will use DLX handler for retry mechanism
                throw new IOException("Size too large");
            } else {
                log.info("Convert to image, creating thumbnail, & publishing : " + p);
                // you must acknowledge that message already processed
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (IOException e) {
            log.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel);
        }
    }

}
