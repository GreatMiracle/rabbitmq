package com.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BindingConfig {

    private final QueueConfig queueConfig;
    private final ExchangeConfig exchangeConfig;

    @Bean
    public Binding bindingAccWithXHr() {
        return BindingBuilder.bind(queueConfig.queueHrAccounting()).to(exchangeConfig.fanoutExchangeHr());
    }

    @Bean
    public Binding bindingMarWithXHr() {
        return BindingBuilder.bind(queueConfig.queueHrMarketing()).to(exchangeConfig.fanoutExchangeHr());
    }

    @Bean
    public Binding bindingImgJPG() {
        return BindingBuilder.bind(queueConfig.queuePictureImage()).to(exchangeConfig.directExchangePicture()).with("jpg");
    }

    @Bean
    public Binding bindingImgPNG() {
        return BindingBuilder.bind(queueConfig.queueHrMarketing()).to(exchangeConfig.directExchangePicture()).with("png");
    }

    @Bean
    public Binding bindingImgSVG() {
        return BindingBuilder.bind(queueConfig.queueHrMarketing()).to(exchangeConfig.directExchangePicture()).with("svg");
    }

    @Bean
    public Binding bindingTopicImgPNG() {
        return BindingBuilder.bind(queueConfig.queuePictureImage()).to(exchangeConfig.topicExchangePicture()).with("*.*.png");
    }

    @Bean
    public Binding bindingTopicImgJPG() {
        return BindingBuilder.bind(queueConfig.queuePictureImage()).to(exchangeConfig.topicExchangePicture()).with("#.jpg");
    }

    @Bean
    public Binding bindingTopicImgSVG() {
        return BindingBuilder.bind(queueConfig.queuePictureVector()).to(exchangeConfig.topicExchangePicture()).with("*.*.svg");
    }

    @Bean
    public Binding bindingTopicImgFilterMobile() {
        return BindingBuilder.bind(queueConfig.queuePictureFilter()).to(exchangeConfig.topicExchangePicture()).with("mobile.#");
    }

    @Bean
    public Binding bindingTopicImgLog() {
        return BindingBuilder.bind(queueConfig.queuePictureLog()).to(exchangeConfig.topicExchangePicture()).with("*.large.svg");
    }

//    @Bean
//    public Binding bindingFanoutMyPictureImg() {
//        return BindingBuilder.bind(queueConfig.queueMyPictureImage()).to(exchangeConfig.fanoutExchangeMyPicture());
//    }

    @Bean
    public Binding bindingFanoutMyPictureImgDLX() {
        return BindingBuilder.bind(queueConfig.queueMyPictureImageDLX()).to(exchangeConfig.fanoutExchangeMyPictureDLX());
    }

        @Bean
    public Binding bindingFanoutMyPictureImg() {
        return BindingBuilder.bind(queueConfig.queueMyPictureImageDLXArg()).to(exchangeConfig.fanoutExchangeMyPicture());
    }

    @Bean
    public Binding bindingFanoutMyPictureImgTTL() {
        return BindingBuilder.bind(queueConfig.queueMyPictureImageTTLArg()).to(exchangeConfig.fanoutExchangeMyPicture());
    }
}
