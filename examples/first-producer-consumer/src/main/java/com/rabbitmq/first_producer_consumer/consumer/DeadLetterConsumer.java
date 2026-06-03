package com.rabbitmq.first_producer_consumer.consumer;


import com.rabbitmq.first_producer_consumer.config.DLQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class DeadLetterConsumer {

    @RabbitListener(
            queues = DLQConfig.DLQ_QUEUE
    )
    public void consumeDeadLetterMessage(
            String message
    ) {

        System.out.println(
                "DEAD LETTER RECEIVED : "
                        + message
        );
    }
}
