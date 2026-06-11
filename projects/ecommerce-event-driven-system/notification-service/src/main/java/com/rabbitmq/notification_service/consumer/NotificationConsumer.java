package com.rabbitmq.notification_service.consumer;

import com.rabbitmq.notification_service.config.RabbitMQConfig;
import com.rabbitmq.notification_service.dto.PaymentCompletedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(
            queues = RabbitMQConfig.PAYMENT_COMPLETED_QUEUE
    )
    public void consumePaymentCompletedEvent(
            PaymentCompletedEvent event
    ) {

        System.out.println();
        System.out.println("NOTIFICATION RECEIVED");
        System.out.println("Order Id : " + event.getOrderId());
        System.out.println("Product  : " + event.getProductName());
        System.out.println("Amount   : " + event.getAmount());
        System.out.println();
        System.out.println("EMAIL SENT");
        System.out.println("SMS SENT");
        System.out.println("ORDER CONFIRMATION SENT");
        System.out.println();
    }
}