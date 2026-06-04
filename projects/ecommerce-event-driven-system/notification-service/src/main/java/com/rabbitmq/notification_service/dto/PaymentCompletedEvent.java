package com.rabbitmq.notification_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCompletedEvent {

    private Long orderId;
    private String productName;
    private Double amount;
}
