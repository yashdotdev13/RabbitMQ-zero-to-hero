package com.rabbitmq.first_producer_consumer.producer;

import com.rabbitmq.first_producer_consumer.config.DLQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DlqProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishMessage(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                DLQConfig.ORDER_QUEUE,
                message
        );

        System.out.println(
                "MESSAGE SENT : "
                        + message
        );
    }
}