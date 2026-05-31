package com.rabbitmq.first_producer_consumer.consumer;

import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SupportConsumer {

    @RabbitListener(
            queues = QueueConfig.SUPPORT_QUEUE
    )
    public void consumeSupport(
            String message
    ) {

        System.out.println(
                "SUPPORT EVENT : "
                        + message
        );
    }
}
