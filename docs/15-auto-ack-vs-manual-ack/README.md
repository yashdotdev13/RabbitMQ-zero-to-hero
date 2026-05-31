# Auto Acknowledgement vs Manual Acknowledgement

## Learning Objectives

After completing this chapter, you will understand:

* What Auto Acknowledgement is
* What Manual Acknowledgement is
* How RabbitMQ handles message delivery
* How RabbitMQ deletes messages
* Why acknowledgements are important
* How to configure Manual Ack in Spring Boot
* How `basicAck()` works
* Production best practices for acknowledgements

---

# Why This Chapter Matters

Acknowledgements are one of the most important reliability features in RabbitMQ.

Without acknowledgements:

```text
Consumer Crash
        ↓
Message Lost
```

With acknowledgements:

```text
Consumer Crash
        ↓
No ACK
        ↓
Message Requeued
        ↓
Message Safe
```

This mechanism is one of the primary reasons RabbitMQ is widely used in production systems.

---

# Auto Acknowledgement

Auto Ack means RabbitMQ considers a message successfully processed immediately after delivering it to a consumer.

RabbitMQ removes the message without waiting for any confirmation.

---

## Auto Ack Flow

![Auto Ack Flow](../../diagrams/chapter-15/01-auto-ack-flow.png)

```text
Producer
    ↓
Queue
    ↓
Consumer
    ↓
RabbitMQ Deletes Message
```

If the consumer crashes after receiving the message:

```text
❌ Message Lost
```

---

# Advantages Of Auto Ack

### Simple

No acknowledgement handling is required.

### Fast

RabbitMQ does not wait for confirmation.

### Less Code

Consumers remain very simple.

---

# Disadvantages Of Auto Ack

### Message Loss

Consumer failures can result in permanent message loss.

### Not Reliable

Unsuitable for business-critical systems.

### Poor Recovery

Failed processing cannot be recovered automatically.

---

# Manual Acknowledgement

Manual Ack means RabbitMQ waits for explicit confirmation from the consumer before deleting a message.

The consumer must send:

```java
channel.basicAck(...)
```

to confirm successful processing.

---

## Manual Ack Flow

![Manual Ack Flow](../../diagrams/chapter-15/02-manual-ack-flow.png)

```text
Producer
    ↓
Queue
    ↓
Consumer
    ↓
Process Message
    ↓
basicAck()
    ↓
RabbitMQ Deletes Message
```

This is the preferred production approach.

---

# Consumer Crash Before ACK

![Consumer Crash Before ACK](../../diagrams/chapter-15/03-consumer-crash-before-ack.png)

If the consumer crashes before sending an acknowledgement:

```text
No ACK Received
```

RabbitMQ keeps ownership of the message and can deliver it again.

This prevents data loss.

---

# Message Requeue Process

![Message Requeue Process](../../diagrams/chapter-15/04-message-requeue-process.png)

```text
Message Received
        ↓
Consumer Crash
        ↓
No ACK
        ↓
RabbitMQ Requeues Message
```

The message remains available for future processing.

---

# Auto Ack vs Manual Ack

![Auto Ack vs Manual Ack](../../diagrams/chapter-15/05-auto-vs-manual-practical.png)

| Feature           | Auto Ack | Manual Ack |
| ----------------- | -------- | ---------- |
| Easy To Implement | ✅        | ❌          |
| Fast              | ✅        | ❌          |
| Reliable          | ❌        | ✅          |
| Message Safety    | ❌        | ✅          |
| Production Ready  | ❌        | ✅          |
| Crash Recovery    | ❌        | ✅          |

---

# Production Recommendation

![Production Recommendation](../../diagrams/chapter-15/06-production-recommendation.png)

Use Auto Ack for:

* Analytics
* Metrics
* Logging
* Monitoring Events

Use Manual Ack for:

* Orders
* Payments
* Inventory
* Notifications
* Banking Events

---

# Practical Implementation

For this chapter we implemented:

```text
auto-ack.queue

manual-ack.queue
```

to compare both acknowledgement strategies.

---

# Queue Verification

## Auto Ack Queue

![Auto Ack Queue](../../assets/chapter-15/01-auto-ack-queue-created.png)

RabbitMQ successfully created:

```text
auto-ack.queue
```

---

## Manual Ack Queue

![Manual Ack Queue](../../assets/chapter-15/02-manual-ack-queue-created.png)

RabbitMQ successfully created:

```text
manual-ack.queue
```

---

# Auto Ack Test

API:

```http
POST /messages/ack/auto?message=OrderCreated
```

## Publishing Message

![Auto Ack Message Published](../../assets/chapter-15/03-auto-ack-message-published.png)

## Consumer Output

![Auto Ack Consumer Output](../../assets/chapter-15/04-auto-ack-consumer-output.png)

```text
AUTO ACK RECEIVED : OrderCreated
```

---

# Manual Ack Configuration

To enable Manual Ack we created a dedicated listener container factory.

```java
factory.setAcknowledgeMode(
        AcknowledgeMode.MANUAL
);
```

## Verification

![Manual Ack Container Factory](../../assets/chapter-15/07-manual-ack-container-factory.png)

This tells Spring AMQP not to automatically acknowledge messages.

---

# Manual Ack Implementation

The consumer explicitly acknowledges messages.

```java
channel.basicAck(
        deliveryTag,
        false
);
```

This tells RabbitMQ:

```text
Message Successfully Processed
```

---

## Verification

![basicAck Implementation](../../assets/chapter-15/08-basic-ack-implementation.png)

---

# Manual Ack Test

API:

```http
POST /messages/ack/manual?message=PaymentCompleted
```

## Publishing Message

![Manual Ack Message Published](../../assets/chapter-15/05-manual-ack-message-published.png)

## Consumer Output

![Manual Ack Consumer Output](../../assets/chapter-15/06-manual-ack-consumer-output.png)

```text
MANUAL ACK RECEIVED : PaymentCompleted

ACK SENT FOR : PaymentCompleted
```

---

# Real World Example

Consider an E-Commerce platform.

Event:

```text
OrderCreated
```

Consumer:

```text
Inventory Service
```

### Auto Ack

```text
Message Removed
Consumer Crashed
Order Lost
```

### Manual Ack

```text
No ACK Sent
Message Requeued
Order Safe
```

Manual Ack protects critical business operations.

---

# Production Best Practices

## Prefer Manual Ack

Always use Manual Ack for:

* Orders
* Payments
* Inventory
* Notifications

## Use Auto Ack Carefully

Auto Ack should only be used when occasional message loss is acceptable.

## Design For Failures

Assume:

* Applications crash
* Servers restart
* Networks fail

RabbitMQ acknowledgements help recover from these situations.

---

# Interview Questions

1. What is an acknowledgement in RabbitMQ?
2. What is Auto Ack?
3. What is Manual Ack?
4. What is `basicAck()`?
5. Why is Manual Ack more reliable?
6. When should Auto Ack be used?
7. What happens if a consumer crashes before ACK?
8. What is `deliveryTag`?
9. Why is Manual Ack preferred in production?
10. Explain RabbitMQ acknowledgement flow.

---

# Key Takeaways

* Auto Ack is simple but risky.
* Manual Ack is safer and production-ready.
* `basicAck()` confirms successful processing.
* RabbitMQ deletes messages only after acknowledgement in Manual Ack mode.
* Manual Ack is the preferred approach for critical business workflows.

---

# Chapter Summary

In this chapter, we implemented and compared:

```text
Auto Ack
      vs
Manual Ack
```

We learned:

* Queue Configuration
* Manual Ack Setup
* `AcknowledgeMode.MANUAL`
* `basicAck()`
* Reliability Patterns
* Production Recommendations

Most importantly:

```text
Message Delivered
        ↓
Message Processed
        ↓
basicAck()
        ↓
RabbitMQ Deletes Message
```

This acknowledgement mechanism is fundamental to building reliable event-driven systems with RabbitMQ.

---

# What's Next?

## Chapter 16 → Message Requeue & Redelivery

Topics Covered:

* Consumer Crash Simulation
* Message Requeue
* Message Redelivery
* `basicNack()`
* `basicReject()`
* Failure Recovery Patterns

In the next chapter, we will intentionally fail consumers and observe how RabbitMQ safely re-delivers messages.
