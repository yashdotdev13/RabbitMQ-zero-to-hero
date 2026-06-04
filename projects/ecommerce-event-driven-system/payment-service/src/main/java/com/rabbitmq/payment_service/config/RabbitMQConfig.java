package com.rabbitmq.payment_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PAYMENT_QUEUE =
            "payment.queue";

    public static final String PAYMENT_COMPLETED_QUEUE =
            "payment.completed.queue";

    public static final String PAYMENT_EXCHANGE =
            "payment.exchange";

    public static final String PAYMENT_COMPLETED_ROUTING_KEY =
            "payment.completed";

    @Bean
    public Queue paymentCompletedQueue() {
        System.out.println("CREATING PAYMENT COMPLETED QUEUE");
        return new Queue(PAYMENT_COMPLETED_QUEUE);
    }

    @Bean
    public DirectExchange paymentExchange() {
        System.out.println("CREATING PAYMENT EXCHANGE");
        return new DirectExchange(PAYMENT_EXCHANGE);
    }

    @Bean
    public Binding paymentCompletedBinding() {

        return BindingBuilder
                .bind(paymentCompletedQueue())
                .to(paymentExchange())
                .with(PAYMENT_COMPLETED_ROUTING_KEY);
    }
}