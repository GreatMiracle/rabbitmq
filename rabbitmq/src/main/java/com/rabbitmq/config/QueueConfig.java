package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    public Queue myQueue() {
        return new Queue("my-queue");
    }

    @Bean
    public Queue myStudent() {
        return new Queue("my-student");
    }

    @Bean
    public Queue queueHrMarketing() {
        return new Queue("q.hr.marketing");
    }

    @Bean
    public Queue queueHrAccounting() {
        return new Queue("q.hr.accounting");
    }

    @Bean
    public Queue queuePictureImage() {
        return new Queue("q.picture.image");
    }

    @Bean
    public Queue queuePictureVector() {
        return new Queue("q.picture.vector");
    }

    @Bean
    public Queue queuePictureFilter() {
        return new Queue("q.picture.filter");
    }

    @Bean
    public Queue queuePictureLog() {
        return new Queue("q.picture.log");
    }

//    @Bean
//    public Queue queueMyPictureImage() {
//        return new Queue("q.mypicture.image");
//    }

    @Bean
    public Queue queueMyPictureImageDLX() {
        return new Queue("q.mypicture.dlx");
    }

    @Bean
    public Queue queueMyPictureImageDLXArg() {
        return QueueBuilder.durable("q.mypicture.image")
                .withArgument("x-dead-letter-exchange", "q.mypicture.dlx")
                .build();
    }


}
