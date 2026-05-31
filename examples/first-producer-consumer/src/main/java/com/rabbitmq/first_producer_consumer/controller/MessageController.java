package com.rabbitmq.first_producer_consumer.controller;

import com.rabbitmq.first_producer_consumer.producer.MessageProducer;
import com.rabbitmq.first_producer_consumer.producer.QueueProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer messageProducer;
    private final QueueProducer producer;

    @PostMapping
    public String sendMessage(@RequestParam String message) {
        messageProducer.sendMessage(message);
        return "Message Published Successfully";
    }

    @PostMapping("/topic/order/create")
    public String publishOrderCreated(
            @RequestParam String message
    ) {

        producer.publishOrderCreated(message);

        return "Order Created Event Published Successfully";
    }

    @PostMapping("/topic/order/update")
    public String publishOrderUpdated(
            @RequestParam String message
    ) {

        producer.publishOrderUpdated(message);

        return "Order Updated Event Published Successfully";
    }

    @PostMapping("/topic/payment/complete")
    public String publishPaymentCompleted(
            @RequestParam String message
    ) {

        producer.publishPaymentCompleted(message);

        return "Payment Completed Event Published Successfully";
    }

    @PostMapping("/topic/payment/fail")
    public String publishPaymentFailed(
            @RequestParam String message
    ) {

        producer.publishPaymentFailed(message);

        return "Payment Failed Event Published Successfully";
    }


    @PostMapping("/headers/finance")
    public String publishFinanceMessage(
            @RequestParam String message
    ) {

        producer.publishFinanceMessage(
                message
        );

        return "Finance Message Published Successfully";
    }

    @PostMapping("/headers/support")
    public String publishSupportMessage(
            @RequestParam String message
    ) {

        producer.publishSupportMessage(
                message
        );

        return "Support Message Published Successfully";
    }

    @PostMapping("/ack")
    public String publishAckMessage(
            @RequestParam String message
    ) {

        messageProducer.publishAckMessage(
                message
        );

        return "Ack Demo Message Published";
    }
}