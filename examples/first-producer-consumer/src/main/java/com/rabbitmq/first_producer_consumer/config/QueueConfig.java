package com.rabbitmq.first_producer_consumer.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String ORDERS_QUEUE = "orders.queue";
    public static final String NOTIFICATIONS_QUEUE = "notifications.queue";

    public static final String AUDIT_QUEUE = "audit.queue";

    public static final String UPDATES_QUEUE = "updates.queue";

    public static final String CANCELLED_QUEUE = "cancelled-queue";

    @Bean
    public Queue ordersQueue() {
        return new Queue(ORDERS_QUEUE, true);
    }

    @Bean
    public Queue notificationsQueue() {
        return new Queue(NOTIFICATIONS_QUEUE, true);
    }

    @Bean
    public Queue auditQueue() {
        return new Queue(AUDIT_QUEUE, true);
    }

    @Bean
    public Queue updatesQueue() {
        return new Queue(UPDATES_QUEUE, true);
    }

    @Bean
    public Queue cancelledQueue() {
        return new Queue(
                CANCELLED_QUEUE,
                true
        );
    }
}