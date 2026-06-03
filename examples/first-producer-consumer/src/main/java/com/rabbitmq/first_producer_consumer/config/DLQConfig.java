package com.rabbitmq.first_producer_consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DLQConfig {

    public static final String ORDER_QUEUE =
            "order.queue";

    public static final String DLX_EXCHANGE =
            "dead-letter.exchange";

    public static final String DLQ_QUEUE =
            "dead-letter.queue";

    public static final String DLQ_ROUTING_KEY =
            "dead-letter.routing";


    @Bean
    public DirectExchange deadLetterExchange() {

        return new DirectExchange(
                DLX_EXCHANGE
        );
    }

    @Bean
    public Queue deadLetterQueue() {

        return QueueBuilder
                .durable(
                        DLQ_QUEUE
                )
                .build();
    }


    @Bean
    public Queue orderQueue() {

        return QueueBuilder
                .durable(
                        ORDER_QUEUE
                )
                .deadLetterExchange(
                        DLX_EXCHANGE
                )
                .deadLetterRoutingKey(
                        DLQ_ROUTING_KEY
                )
                .build();
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder
                .bind(
                        deadLetterQueue()
                )
                .to(
                        deadLetterExchange()
                )
                .with(
                        DLQ_ROUTING_KEY
                );
    }
}
