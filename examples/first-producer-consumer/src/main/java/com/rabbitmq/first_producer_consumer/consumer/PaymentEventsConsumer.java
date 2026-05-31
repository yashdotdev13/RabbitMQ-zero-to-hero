package com.rabbitmq.first_producer_consumer.consumer;

import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventsConsumer {

    @RabbitListener(
            queues = QueueConfig.PAYMENT_EVENTS_QUEUE
    )
    public void consumePaymentEvent(
            String message
    ) {

        System.out.println(
                "PAYMENT EVENT : "
                        + message
        );
    }
}