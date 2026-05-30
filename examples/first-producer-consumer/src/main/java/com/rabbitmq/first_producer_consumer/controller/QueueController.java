package com.rabbitmq.first_producer_consumer.controller;


import com.rabbitmq.first_producer_consumer.producer.QueueProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queues")
@RequiredArgsConstructor
public class QueueController {

    private final QueueProducer producer;

    @PostMapping("/orders")
    public String publishOrderMessage(
            @RequestParam String message
    ) {

        producer.sendOrdersMessage(message);
        return "Order Message Published";
    }

    @PostMapping("/notifications")
    public String publishNotificationMessage(
            @RequestParam String message
    ) {

        producer.sendNotificationMessage(message);
        return "Notification Message Published";
    }


    @PostMapping("/orders/update")
    public String publishOrderUpdate(
            @RequestParam String message
    ) {
        producer.sendOrderUpdateMessage(
                message
        );
        return "Order Update Published";
    }


    @PostMapping("/orders/cancel")
    public String cancelOrder(
            @RequestParam String message
    ) {

        producer.sendOrderCancelledMessage(
                message
        );
        return "Order Cancelled Event Published";
    }
}