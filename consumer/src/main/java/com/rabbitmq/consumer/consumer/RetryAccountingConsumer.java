package com.rabbitmq.consumer.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.consumer.config.DlxProcessingErrorHandler;
import com.rabbitmq.library.message.Employee;
import com.rabbitmq.library.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RetryAccountingConsumer {

    private static final String DEAD_EXCHANGE_NAME = "x.guideline2.dead";
    private static final String ROUTING_KEY = "accounting";
    private final DlxProcessingErrorHandler dlxFanoutProcessingErrorHandler;

    public RetryAccountingConsumer() {
        this.dlxFanoutProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME, ROUTING_KEY);
    }

    @RabbitListener(queues = "q.guideline2.accounting.work")
    public void listen(Message message, Channel channel)
            throws InterruptedException {
        try {
            var emp = JsonUtils.convertByteToObject(message.getBody(), Employee.class);

            if (StringUtils.isEmpty(emp.getName())) {
                throw new IllegalArgumentException("Name is empty");
            } else {
                log.info("On accounting : {}", emp);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            log.warn("Error processing message : {} : {}", new String(message.getBody()), e.getMessage());
            dlxFanoutProcessingErrorHandler.handleErrorProcessingMessageWithRoutingKey(message, channel);
        }

    }
}
