package com.rabbitmq.order_service.controller;

import com.rabbitmq.order_service.dto.OrderCreatedEvent;
import com.rabbitmq.order_service.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping
    public String createOrder(
            @RequestBody OrderCreatedEvent event
    ) {

        orderProducer.publishOrderCreatedEvent(event);

        return "Order Created Event Published Successfully";
    }
}