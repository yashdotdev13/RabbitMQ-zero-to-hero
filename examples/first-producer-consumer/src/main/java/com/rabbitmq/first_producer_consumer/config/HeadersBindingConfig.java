package com.rabbitmq.first_producer_consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HeadersBindingConfig {

    @Bean
    public Binding financeBinding(
            Queue financeQueue,
            HeadersExchange headersExchange
    ) {

        Map<String, Object> headers =
                new HashMap<>();

        headers.put(
                "department",
                "finance"
        );

        headers.put(
                "priority",
                "high"
        );

        return BindingBuilder
                .bind(financeQueue)
                .to(headersExchange)
                .whereAll(headers)
                .match();
    }

    @Bean
    public Binding supportBinding(
            Queue supportQueue,
            HeadersExchange headersExchange
    ) {

        Map<String, Object> headers =
                new HashMap<>();

        headers.put(
                "department",
                "support"
        );

        headers.put(
                "priority",
                "high"
        );

        return BindingBuilder
                .bind(supportQueue)
                .to(headersExchange)
                .whereAny(headers)
                .match();
    }
}