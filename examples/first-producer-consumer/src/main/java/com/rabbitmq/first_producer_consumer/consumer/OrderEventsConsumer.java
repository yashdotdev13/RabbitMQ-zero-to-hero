package com.rabbitmq.first_producer_consumer.consumer;

import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventsConsumer {

    @RabbitListener(
            queues = QueueConfig.ORDER_EVENTS_QUEUE
    )
    public void consumeOrderEvent(
            String message
    ) {

        System.out.println(
                "ORDER EVENT : "
                        + message
        );
    }
}