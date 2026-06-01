package com.rabbitmq.first_producer_consumer.consumer;


import com.rabbitmq.client.Channel;
import com.rabbitmq.first_producer_consumer.config.QueueConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RequeueConsumer {

    private final AtomicInteger attemptCounter =
            new AtomicInteger(0);

    @RabbitListener(
            queues = QueueConfig.MANUAL_ACK_QUEUE,
            containerFactory = "manualAckContainerFactory"
    )
    public void consumeMessage(
            Message message,
            Channel channel
    ) throws IOException {

        String body =
                new String(
                        message.getBody(),
                        StandardCharsets.UTF_8
                );

        long deliveryTag =
                message.getMessageProperties()
                        .getDeliveryTag();

        int attempt =
                attemptCounter.incrementAndGet();

        System.out.println("\n=================================");
        System.out.println(
                "Attempt : " + attempt
        );
        System.out.println(
                "Received Message : " + body
        );

        // Simulate failure for first 2 attempts
        if (attempt < 3) {

            System.out.println(
                    "Processing Failed"
            );

            System.out.println(
                    "Sending NACK with requeue=true"
            );

            channel.basicNack(
                    deliveryTag,
                    false,
                    true
            );

            System.out.println(
                    "Message Requeued"
            );

            System.out.println(
                    "=================================\n"
            );

            return;
        }

        // Success on third attempt
        System.out.println(
                "Processing Successful"
        );

        channel.basicAck(
                deliveryTag,
                false
        );

        System.out.println(
                "ACK Sent Successfully"
        );

        System.out.println(
                "=================================\n"
        );
    }
}
