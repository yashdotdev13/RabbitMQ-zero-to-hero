package com.rabbitmq.first_producer_consumer.consumer;

import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AuditConsumer {

    @RabbitListener(
            queues = QueueConfig.AUDIT_QUEUE
    )
    public void consumeAuditMessage(
            String message
    ) {
        System.out.println(
                "AUDIT EVENT : " + message
        );
    }
}
