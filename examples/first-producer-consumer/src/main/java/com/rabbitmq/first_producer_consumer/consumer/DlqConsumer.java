package com.rabbitmq.first_producer_consumer.consumer;


import com.rabbitmq.first_producer_consumer.config.DLQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class DlqConsumer {


    @RabbitListener(
            queues = DLQConfig.ORDER_QUEUE
    )
    public void consumeMessage(
            String message
    ) {

        System.out.println(
                "Received : "
                        + message
        );

        throw new RuntimeException(
                "Simulated Failure"
        );
    }
}
