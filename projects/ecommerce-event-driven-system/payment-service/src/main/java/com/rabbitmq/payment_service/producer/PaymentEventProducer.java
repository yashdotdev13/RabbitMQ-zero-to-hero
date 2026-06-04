package com.rabbitmq.payment_service.producer;

import com.rabbitmq.payment_service.config.RabbitMQConfig;
import com.rabbitmq.payment_service.dto.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishPaymentCompletedEvent(
            PaymentCompletedEvent event
    ) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PAYMENT_EXCHANGE,
                RabbitMQConfig.PAYMENT_COMPLETED_ROUTING_KEY,
                event
        );

        System.out.println();
        System.out.println("PAYMENT COMPLETED EVENT PUBLISHED");
        System.out.println(event);
        System.out.println();
    }
}