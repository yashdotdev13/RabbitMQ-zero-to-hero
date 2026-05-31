package com.rabbitmq.first_producer_consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicBindingConfig {

    @Bean
    public Binding orderTopicBinding(
            @Qualifier("orderEventsQueue")
            Queue orderQueue,

            TopicExchange businessTopicExchange
    ) {

        return BindingBuilder
                .bind(orderQueue)
                .to(businessTopicExchange)
                .with("order.*");
    }

    @Bean
    public Binding paymentTopicBinding(
            @Qualifier("paymentEventsQueue")
            Queue paymentQueue,

            TopicExchange businessTopicExchange
    ) {

        return BindingBuilder
                .bind(paymentQueue)
                .to(businessTopicExchange)
                .with("payment.*");
    }
}