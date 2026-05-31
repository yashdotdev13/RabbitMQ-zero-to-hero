package com.rabbitmq.first_producer_consumer.producer;


import com.rabbitmq.first_producer_consumer.config.*;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendOrdersMessage(String message){
//        rabbitTemplate.convertAndSend(
//                QueueConfig.ORDERS_QUEUE,
//                message
//        );
        rabbitTemplate.convertAndSend(
                ExchangeConfig.ORDER_EXCHANGE,
                ExchangeConfig.ORDER_ROUTING_KEY,
                message
        );
        System.out.println(
                "Order Message Sent : " + message
        );
    }

    public void sendNotificationMessage(String message){
        rabbitTemplate.convertAndSend(
                QueueConfig.NOTIFICATIONS_QUEUE,
                message
        );
        System.out.println(
                "Notification Message Sent : " + message
        );
    }


    public void sendOrderUpdateMessage(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                ExchangeConfig.ORDER_EXCHANGE,
                ExchangeConfig.ORDER_UPDATED_KEY,
                message
        );

        System.out.println(
                "Order Update Sent : " + message
        );
    }


    public void sendOrderCancelledMessage(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                ExchangeConfig.ORDER_EXCHANGE,
                ExchangeConfig.ORDER_CANCELLED_KEY,
                message
        );

        System.out.println(
                "Order Cancelled Event Sent : "
                        + message
        );
    }


    public void publishNotification(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                FanoutExchangeConfig.NOTIFICATION_EXCHANGE,
                "",
                message
        );

        System.out.println(
                "Notification Published : "
                        + message
        );
    }

    public void publishOrderCreated(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                TopicExchangeConfig.BUSINESS_TOPIC_EXCHANGE,
                "order.created",
                message
        );
    }

    public void publishOrderUpdated(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                TopicExchangeConfig.BUSINESS_TOPIC_EXCHANGE,
                "order.updated",
                message
        );
    }

    public void publishPaymentCompleted(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                TopicExchangeConfig.BUSINESS_TOPIC_EXCHANGE,
                "payment.completed",
                message
        );
    }

    public void publishPaymentFailed(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                TopicExchangeConfig.BUSINESS_TOPIC_EXCHANGE,
                "payment.failed",
                message
        );
    }


    public void publishFinanceMessage(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                HeadersExchangeConfig.HEADERS_EXCHANGE,
                "",
                message,
                msg -> {

                    msg.getMessageProperties()
                            .setHeader(
                                    "department",
                                    "finance"
                            );

                    msg.getMessageProperties()
                            .setHeader(
                                    "priority",
                                    "high"
                            );

                    return msg;
                }
        );

        System.out.println(
                "Finance Message Sent : "
                        + message
        );
    }


    public void publishSupportMessage(
            String message
    ) {

        rabbitTemplate.convertAndSend(
                HeadersExchangeConfig.HEADERS_EXCHANGE,
                "",
                message,
                msg -> {

                    msg.getMessageProperties()
                            .setHeader(
                                    "department",
                                    "support"
                            );

                    msg.getMessageProperties()
                            .setHeader(
                                    "priority",
                                    "low"
                            );

                    return msg;
                }
        );

        System.out.println(
                "Support Message Sent : "
                        + message
        );
    }
}
