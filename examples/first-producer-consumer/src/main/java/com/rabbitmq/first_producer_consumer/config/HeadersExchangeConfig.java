package com.rabbitmq.first_producer_consumer.config;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadersExchangeConfig {

    public static final String HEADERS_EXCHANGE =
            "business.headers.exchange";

    @Bean
    public HeadersExchange headersExchange() {

        return new HeadersExchange(
                HEADERS_EXCHANGE
        );
    }
}