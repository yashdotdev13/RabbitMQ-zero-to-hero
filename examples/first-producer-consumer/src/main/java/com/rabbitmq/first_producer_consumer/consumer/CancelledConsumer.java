package com.rabbitmq.first_producer_consumer.consumer;

import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CancelledConsumer {

    @RabbitListener(
            queues = QueueConfig.CANCELLED_QUEUE
    )
    public void consumeCancelledMessage(
            String message
    ) {

        System.out.println(
                "CANCELLED EVENT : " + message
        );
    }
}