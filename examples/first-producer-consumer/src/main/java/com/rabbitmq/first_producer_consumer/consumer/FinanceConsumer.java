package com.rabbitmq.first_producer_consumer.consumer;

import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FinanceConsumer {

    @RabbitListener(
            queues = QueueConfig.FINANCE_QUEUE
    )
    public void consumeFinance(
            String message
    ) {

        System.out.println(
                "FINANCE EVENT : "
                        + message
        );
    }
}